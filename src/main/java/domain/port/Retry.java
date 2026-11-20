package domain.port;

import java.time.Duration;
import java.util.function.Supplier;

public interface Retry {

    <T> T executeWithRetry(Supplier<T> operation, Class<? extends Exception>... transientExceptions);

    int getMaxAttempts();

    Duration getInitialInterval();

    double getMultiplier();

    Duration getMaxInterval();

    boolean isRetryEnabled();
}