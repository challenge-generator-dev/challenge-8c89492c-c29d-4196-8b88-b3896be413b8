// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapter;

import domain.model.PaymentFailedException;
import domain.model.PaymentRequest;
import domain.model.PaymentResponse;
import domain.port.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceClient {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceClient.class);
    private static final int DEFAULT_MAX_RETRIES = 3;
    private static final long INITIAL_INTERVAL_MS = 500;
    private static final double MULTIPLIER = 2.0;
    private static final long MAX_INTERVAL_MS = 5000;

    private final Retry retry;

    public PaymentServiceClient(Retry retry) {
        this.retry = retry;
    }

    public PaymentResponse processPayment(PaymentRequest request) throws PaymentFailedException {
      throw new UnsupportedOperationException("TODO(reto): processPayment");
    }

    private PaymentResponse executePayment(PaymentRequest request) {
      throw new UnsupportedOperationException("TODO(reto): executePayment");
    }

    private PaymentResponse callPaymentProvider(PaymentRequest request) {
      throw new UnsupportedOperationException("TODO(reto): callPaymentProvider");
    }

    private boolean isPermanentFailure(PaymentFailedException e) {
      throw new UnsupportedOperationException("TODO(reto): isPermanentFailure");
    }
}