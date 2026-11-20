package domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class DuplicateOperationException extends RuntimeException {
    private final String idempotencyKey;
    private final String transactionId;
    private final LocalDateTime originalOperationTime;
    private final String originalStatus;

    public DuplicateOperationException(String idempotencyKey, String transactionId,
                                      LocalDateTime originalOperationTime, String originalStatus) {
        super(String.format("Duplicate operation detected for idempotency key '%s'. " +
                "Original transaction '%s' was processed at %s with status '%s'",
                idempotencyKey, transactionId, originalOperationTime, originalStatus));
        this.idempotencyKey = Objects.requireNonNull(idempotencyKey, "Idempotency key cannot be null");
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.originalOperationTime = Objects.requireNonNull(originalOperationTime, "Original operation time cannot be null");
        this.originalStatus = Objects.requireNonNull(originalStatus, "Original status cannot be null");
    }

    public DuplicateOperationException(String idempotencyKey, String transactionId,
                                      LocalDateTime originalOperationTime, String originalStatus,
                                      Throwable cause) {
        super(String.format("Duplicate operation detected for idempotency key '%s'. " +
                "Original transaction '%s' was processed at %s with status '%s'",
                idempotencyKey, transactionId, originalOperationTime, originalStatus), cause);
        this.idempotencyKey = Objects.requireNonNull(idempotencyKey, "Idempotency key cannot be null");
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.originalOperationTime = Objects.requireNonNull(originalOperationTime, "Original operation time cannot be null");
        this.originalStatus = Objects.requireNonNull(originalStatus, "Original status cannot be null");
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getOriginalOperationTime() {
        return originalOperationTime;
    }

    public String getOriginalStatus() {
        return originalStatus;
    }

    public boolean isSafeToRetry() {
        return "PENDING".equals(originalStatus) || "FAILED".equals(originalStatus);
    }

    @Override
    public String toString() {
        return "DuplicateOperationException{" +
                "idempotencyKey='" + idempotencyKey + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", originalOperationTime=" + originalOperationTime +
                ", originalStatus='" + originalStatus + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}