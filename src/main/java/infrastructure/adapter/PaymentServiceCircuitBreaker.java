// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapter;

import domain.model.CircuitBreakerOpenException;
import domain.model.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@Component
public class PaymentServiceCircuitBreaker {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceCircuitBreaker.class);
    private static final int DEFAULT_FAILURE_THRESHOLD = 3;
    private static final int DEFAULT_SUCCESS_THRESHOLD = 2;
    private static final Duration DEFAULT_WAIT_DURATION_IN_OPEN_STATE = Duration.ofSeconds(10);
    private static final double DEFAULT_FAILURE_RATE_THRESHOLD = 50.0;

    private final domain.port.CircuitBreaker circuitBreaker;

    private enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }

    private volatile State currentState = State.CLOSED;
    private final AtomicInteger failureCount = new AtomicInteger(0);
    private final AtomicInteger successCount = new AtomicInteger(0);
    private Instant lastFailureTime;
    private final Object stateLock = new Object();

    public PaymentServiceCircuitBreaker(domain.port.CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    public PaymentResponse executeWithCircuitBreaker(Supplier<PaymentResponse> operation)
            throws CircuitBreakerOpenException {
              throw new UnsupportedOperationException("TODO(reto): executeWithCircuitBreaker");
            }

    private void onSuccess() {
      throw new UnsupportedOperationException("TODO(reto): onSuccess");
    }

    private void onFailure() {
      throw new UnsupportedOperationException("TODO(reto): onFailure");
    }

    private boolean shouldAttemptRecovery() {
      throw new UnsupportedOperationException("TODO(reto): shouldAttemptRecovery");
    }

    private Duration calculateRemainingDelay() {
      throw new UnsupportedOperationException("TODO(reto): calculateRemainingDelay");
    }

    private double calculateFailureRate() {
      throw new UnsupportedOperationException("TODO(reto): calculateFailureRate");
    }

    public State getCurrentState() {
        return currentState;
    }

    public int getFailureCount() {
      throw new UnsupportedOperationException("TODO(reto): getFailureCount");
    }

    public void reset() {
      throw new UnsupportedOperationException("TODO(reto): reset");
    }
}