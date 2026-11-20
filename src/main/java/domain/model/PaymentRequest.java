package domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record PaymentRequest(
    @NotNull(message = "Transaction ID cannot be null")
    @Size(min = 1, max = 64, message = "Transaction ID must be between 1 and 64 characters")
    String transactionId,

    @NotNull(message = "Account ID cannot be null")
    @Size(min = 1, max = 64, message = "Account ID must be between 1 and 64 characters")
    String accountId,

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    BigDecimal amount,

    @NotNull(message = "Currency cannot be null")
    @Size(min = 3, max = 3, message = "Currency must be a 3-character code")
    String currency,

    @NotNull(message = "Idempotency key cannot be null")
    @Size(min = 1, max = 64, message = "Idempotency key must be between 1 and 64 characters")
    String idempotencyKey,

    @NotNull(message = "Timestamp cannot be null")
    LocalDateTime timestamp,

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    String description
) {
    public PaymentRequest {
        Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        Objects.requireNonNull(accountId, "Account ID cannot be null");
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(currency, "Currency cannot be null");
        Objects.requireNonNull(idempotencyKey, "Idempotency key cannot be null");
        Objects.requireNonNull(timestamp, "Timestamp cannot be null");

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (currency.length() != 3) {
            throw new IllegalArgumentException("Currency must be a 3-character code");
        }
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }
}