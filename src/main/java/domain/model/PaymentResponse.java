package domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

public record PaymentResponse(
    @NotNull(message = "Transaction ID cannot be null")
    @Size(min = 1, max = 64, message = "Transaction ID must be between 1 and 64 characters")
    String transactionId,

    @NotNull(message = "Status cannot be null")
    PaymentStatus status,

    @NotNull(message = "Timestamp cannot be null")
    LocalDateTime timestamp,

    @Size(max = 255, message = "Message cannot exceed 255 characters")
    String message,

    @Size(max = 64, message = "Provider reference cannot exceed 64 characters")
    String providerReference
) {
    public PaymentResponse {
        Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");
        Objects.requireNonNull(timestamp, "Timestamp cannot be null");
    }

    public enum PaymentStatus {
        SUCCESS,
        FAILED,
        PENDING,
        REJECTED
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getProviderReference() {
        return providerReference;
    }
}