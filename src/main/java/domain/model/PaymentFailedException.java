package domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class PaymentFailedException extends RuntimeException {
    private final String transactionId;
    private final LocalDateTime failureTime;
    private final String providerMessage;

    public PaymentFailedException(String transactionId, String message, String providerMessage) {
        super(message);
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.failureTime = LocalDateTime.now();
        this.providerMessage = Objects.requireNonNull(providerMessage, "Provider message cannot be null");
    }

    public PaymentFailedException(String transactionId, String message, String providerMessage, Throwable cause) {
        super(message, cause);
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.failureTime = LocalDateTime.now();
        this.providerMessage = Objects.requireNonNull(providerMessage, "Provider message cannot be null");
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getFailureTime() {
        return failureTime;
    }

    public String getProviderMessage() {
        return providerMessage;
    }

    @Override
    public String toString() {
        return "PaymentFailedException{" +
                "transactionId='" + transactionId + '\'' +
                ", failureTime=" + failureTime +
                ", providerMessage='" + providerMessage + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}