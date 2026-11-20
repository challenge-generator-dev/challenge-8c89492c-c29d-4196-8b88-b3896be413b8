package infrastructure.adapter;

import domain.port.IdempotencyKeyRepository;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class IdempotencyKeyRepositoryImpl implements IdempotencyKeyRepository {

    private static final Logger log = LoggerFactory.getLogger(IdempotencyKeyRepositoryImpl.class);

    private final Map<String, IdempotencyKeyRecord> storage;
    private final Map<String, ReentrantLock> locks;

    public IdempotencyKeyRepositoryImpl() {
        this.storage = new ConcurrentHashMap<>();
        this.locks = new ConcurrentHashMap<>();
    }

    @Override
    public boolean existsById(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return false;
        }
        boolean exists = storage.containsKey(idempotencyKey);
        log.debug("Checking existence of idempotency key '{}': {}", idempotencyKey, exists);
        return exists;
    }

    @Override
    public Optional<IdempotencyKeyRecord> findById(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return Optional.empty();
        }
        IdempotencyKeyRecord record = storage.get(idempotencyKey);
        log.debug("Finding idempotency key '{}': {}", idempotencyKey, record != null ? "found" : "not found");
        return Optional.ofNullable(record);
    }

    @Override
    public void save(IdempotencyKeyRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Record cannot be null");
        }

        ReentrantLock lock = locks.computeIfAbsent(record.idempotencyKey(), k -> new ReentrantLock());
        lock.lock();
        try {
            LocalDateTime now = LocalDateTime.now();
            IdempotencyKeyRecord recordToSave = new IdempotencyKeyRecord(
                record.idempotencyKey(),
                record.transactionId(),
                record.status(),
                record.createdAt() != null ? record.createdAt() : now,
                now
            );
            storage.put(record.idempotencyKey(), recordToSave);
            log.info("Saved idempotency key '{}' for transaction '{}' with status '{}'",
                record.idempotencyKey(), record.transactionId(), record.status());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void deleteById(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return;
        }

        ReentrantLock lock = locks.computeIfAbsent(idempotencyKey, k -> new ReentrantLock());
        lock.lock();
        try {
            IdempotencyKeyRecord removed = storage.remove(idempotencyKey);
            if (removed != null) {
                log.info("Deleted idempotency key '{}' for transaction '{}'",
                    idempotencyKey, removed.transactionId());
            } else {
                log.debug("Attempted to delete non-existent idempotency key '{}'", idempotencyKey);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getStorageSize() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
        locks.clear();
        log.info("Cleared all idempotency keys from storage");
    }
}