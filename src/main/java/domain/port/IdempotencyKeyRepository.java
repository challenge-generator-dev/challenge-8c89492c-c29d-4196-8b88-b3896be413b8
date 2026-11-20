package domain.port;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IdempotencyKeyRepository {

    boolean existsById(String idempotencyKey);

    Optional<IdempotencyKeyRecord> findById(String idempotencyKey);

    void save(IdempotencyKeyRecord record);

    void deleteById(String idempotencyKey);

    record IdempotencyKeyRecord(
        String idempotencyKey,
        String transactionId,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        public IdempotencyKeyRecord {
            if (idempotencyKey == null || idempotencyKey.isBlank()) {
                throw new IllegalArgumentException("Idempotency key cannot be null or blank");
            }
            if (transactionId == null || transactionId.isBlank()) {
                throw new IllegalArgumentException("Transaction ID cannot be null or blank");
            }
            if (status == null || status.isBlank()) {
                throw new IllegalArgumentException("Status cannot be null or blank");
            }
            if (createdAt == null) {
                throw new IllegalArgumentException("Created at cannot be null");
            }
            if (updatedAt == null) {
                throw new IllegalArgumentException("Updated at cannot be null");
            }
        }

        public boolean isCompleted() {
            return "SUCCESS".equalsIgnoreCase(status) || "REJECTED".equalsIgnoreCase(status);
        }

        public boolean isPending() {
            return "PENDING".equalsIgnoreCase(status);
        }

        public boolean canRetry() {
            return "FAILED".equalsIgnoreCase(status) || "PENDING".equalsIgnoreCase(status);
        }
    }
}