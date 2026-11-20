// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapter;

import domain.model.PaymentResponse;
import domain.model.PaymentTimeoutException;
import domain.port.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

@Component
public class PaymentTimeoutHandler {

    private static final Logger log = LoggerFactory.getLogger(PaymentTimeoutHandler.class);
    private static final String DEFAULT_OPERATION_DESCRIPTION = "Payment processing";

    private final TimeLimiter timeLimiter;
    private final ExecutorService executorService;

    public PaymentTimeoutHandler(TimeLimiter timeLimiter) {
        this.timeLimiter = timeLimiter;
        this.executorService = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r, "payment-timeout-handler");
            t.setDaemon(true);
            return t;
        });
    }

    public PaymentResponse executeWithTimeout(Supplier<PaymentResponse> operation, Duration timeout)
            throws PaymentTimeoutException {
              throw new UnsupportedOperationException("TODO(reto): executeWithTimeout");
            }

    public PaymentResponse executeWithTimeout(Supplier<PaymentResponse> operation) {
      throw new UnsupportedOperationException("TODO(reto): executeWithTimeout");
    }

    private String extractTransactionId(Supplier<PaymentResponse> operation) {
      throw new UnsupportedOperationException("TODO(reto): extractTransactionId");
    }

    public void shutdown() {
      throw new UnsupportedOperationException("TODO(reto): shutdown");
    }

    /**
     * Registra el proveedor de TimeLimiter que PaymentTimeoutHandler necesita por constructor.
     * Sin este bean el contenedor no puede resolver la dependencia y la aplicación no arranca.
     */
    @Configuration
    static class TimeLimiterConfiguration {

        @Bean
        TimeLimiter paymentTimeLimiter() {
            return new ExecutorTimeLimiter(Duration.ofSeconds(5));
        }
    }

    /**
     * Implementación de infraestructura del puerto TimeLimiter basada en un pool de hilos demonio.
     */
    static final class ExecutorTimeLimiter implements TimeLimiter {

        private final ExecutorService pool = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r, "time-limiter");
            t.setDaemon(true);
            return t;
        });
        private volatile Duration defaultTimeout;

        ExecutorTimeLimiter(Duration defaultTimeout) {
            this.defaultTimeout = defaultTimeout;
        }

        @Override
        public <T> T executeWithTimeout(Callable<T> operation, Duration timeout) throws Exception {
            return execute(operation, timeout);
        }

        @Override
        public <T> T executeWithTimeout(Supplier<T> operation, Duration timeout) {
            try {
                return execute((Callable<T>) operation::get, timeout);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public void executeWithTimeout(Runnable operation, Duration timeout) throws Exception {
            execute((Callable<Void>) () -> {
                operation.run();
                return null;
            }, timeout);
        }

        @Override
        public boolean tryExecuteWithTimeout(Supplier<Boolean> operation, Duration timeout) {
            try {
                Boolean result = execute((Callable<Boolean>) operation::get, timeout);
                return Boolean.TRUE.equals(result);
            } catch (Exception e) {
                log.debug("Operación no completada dentro del tiempo límite: {}", e.getMessage());
                return false;
            }
        }

        @Override
        public Duration getDefaultTimeout() {
            return defaultTimeout;
        }

        @Override
        public void setDefaultTimeout(Duration timeout) {
            this.defaultTimeout = timeout;
        }

        private <T> T execute(Callable<T> operation, Duration timeout) throws Exception {
            Future<T> future = pool.submit(operation);
            try {
                return future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                future.cancel(true);
                throw e;
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause instanceof Exception ex) {
                    throw ex;
                }
                throw new IllegalStateException(cause);
            }
        }
    }
}