package domain.model;

import java.time.Duration;
import java.util.Objects;

public class CircuitBreakerOpenException extends RuntimeException {
    private final String circuitBreakerName;
    private final Duration remainingDelay;
    private final int failureCount;
    private final double failureRate;

    public CircuitBreakerOpenException(String circuitBreakerName, Duration remainingDelay,
                                      int failureCount, double failureRate) {
        super(String.format("Circuit breaker '%s' is open. Remaining delay: %d ms, " +
                "failure count: %d, failure rate: %.2f%%",
                circuitBreakerName, remainingDelay.toMillis(), failureCount, failureRate));
        this.circuitBreakerName = Objects.requireNonNull(circuitBreakerName, "Circuit breaker name cannot be null");
        this.remainingDelay = Objects.requireNonNull(remainingDelay, "Remaining delay cannot be null");
        this.failureCount = failureCount;
        this.failureRate = failureRate;

        if (remainingDelay.isNegative()) {
            throw new IllegalArgumentException("Remaining delay cannot be negative");
        }
        if (failureCount < 0) {
            throw new IllegalArgumentException("Failure count cannot be negative");
        }
        if (failureRate < 0 || failureRate > 100) {
            throw new IllegalArgumentException("Failure rate must be between 0 and 100");
        }
    }

    public String getCircuitBreakerName() {
        return circuitBreakerName;
    }

    public Duration getRemainingDelay() {
        return remainingDelay;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public double getFailureRate() {
        return failureRate;
    }

    public boolean isRetryAllowed() {
        return remainingDelay.isZero();
    }

    public String getFormattedMessage() {
        return String.format("Circuit breaker '%s' is currently open. " +
                "Please retry after %d milliseconds. Current failure rate: %.2f%% (%d failures)",
                circuitBreakerName, remainingDelay.toMillis(), failureRate, failureCount);
    }
}