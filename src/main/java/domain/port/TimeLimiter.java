package domain.port;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public interface TimeLimiter {

    <T> T executeWithTimeout(Callable<T> operation, Duration timeout) throws Exception;

    <T> T executeWithTimeout(Supplier<T> operation, Duration timeout);

    void executeWithTimeout(Runnable operation, Duration timeout) throws Exception;

    boolean tryExecuteWithTimeout(Supplier<Boolean> operation, Duration timeout);

    Duration getDefaultTimeout();

    void setDefaultTimeout(Duration timeout);
}