package domain.model;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class PaymentTimeoutException extends RuntimeException {
    private final String transactionId;
    private final Duration timeoutDuration;
    private final String operationDescription;
    private final long elapsedTimeMillis;

    public PaymentTimeoutException(String transactionId, Duration timeoutDuration,
                                 String operationDescription, long elapsedTimeMillis) {
        super(String.format("Payment operation '%s' for transaction '%s' timed out after %d ms (configured timeout: %d ms)",
                operationDescription, transactionId, elapsedTimeMillis, timeoutDuration.toMillis()));
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.timeoutDuration = Objects.requireNonNull(timeoutDuration, "Timeout duration cannot be null");
        this.operationDescription = Objects.requireNonNull(operationDescription, "Operation description cannot be null");
        this.elapsedTimeMillis = elapsedTimeMillis;

        if (timeoutDuration.isNegative() || timeoutDuration.isZero()) {
            throw new IllegalArgumentException("Timeout duration must be positive");
        }
        if (elapsedTimeMillis < 0) {
            throw new IllegalArgumentException("Elapsed time cannot be negative");
        }
    }

    public PaymentTimeoutException(String transactionId, Duration timeoutDuration,
                                 String operationDescription, long elapsedTimeMillis, Throwable cause) {
        super(String.format("Payment operation '%s' for transaction '%s' timed out after %d ms (configured timeout: %d ms)",
                operationDescription, transactionId, elapsedTimeMillis, timeoutDuration.toMillis()), cause);
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.timeoutDuration = Objects.requireNonNull(timeoutDuration, "Timeout duration cannot be null");
        this.operationDescription = Objects.requireNonNull(operationDescription, "Operation description cannot be null");
        this.elapsedTimeMillis = elapsedTimeMillis;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Duration getTimeoutDuration() {
        return timeoutDuration;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public long getElapsedTimeMillis() {
        return elapsedTimeMillis;
    }

    public boolean isTimeoutExceeded() {
        return elapsedTimeMillis >= timeoutDuration.toMillis();
    }

    public static PaymentTimeoutException fromTimeoutException(String transactionId,
                                                              Duration timeoutDuration,
                                                              String operationDescription,
                                                              TimeoutException cause) {
        return new PaymentTimeoutException(
                transactionId,
                timeoutDuration,
                operationDescription,
                timeoutDuration.toMillis(),
                cause
        );
    }
}