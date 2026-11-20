package domain.port;

import java.time.Duration;
import java.util.function.Supplier;

public interface CircuitBreaker {

    enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }

    String getName();

    State getCurrentState();

    void onSuccess();

    void onFailure();

    boolean shouldAttemptRecovery();

    Duration calculateRemainingDelay();

    double calculateFailureRate();

    int getFailureCount();

    void reset();

    <T> T execute(Supplier<T> operation);
}