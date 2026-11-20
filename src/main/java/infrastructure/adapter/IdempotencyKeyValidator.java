// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapter;

import domain.model.DuplicateOperationException;
import domain.port.IdempotencyKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class IdempotencyKeyValidator {

    private static final Logger log = LoggerFactory.getLogger(IdempotencyKeyValidator.class);

    private final IdempotencyKeyRepository repository;
    private final ReentrantLock lock = new ReentrantLock();

    public IdempotencyKeyValidator(IdempotencyKeyRepository repository) {
        this.repository = repository;
    }

    public void validateIdempotencyKey(String key, String operationId) throws DuplicateOperationException {
      throw new UnsupportedOperationException("TODO(reto): validateIdempotencyKey");
    }

    public boolean isKeyValid(String key) {
      throw new UnsupportedOperationException("TODO(reto): isKeyValid");
    }

    public void markAsCompleted(String key, String status) {
      throw new UnsupportedOperationException("TODO(reto): markAsCompleted");
    }

    public void removeIdempotencyKey(String key) {
      throw new UnsupportedOperationException("TODO(reto): removeIdempotencyKey");
    }
}