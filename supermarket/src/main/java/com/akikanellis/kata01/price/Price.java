package com.akikanellis.kata01.price;

import java.math.BigDecimal;
import java.util.Objects;

import static com.akikanellis.kata01.utils.Preconditions.checkNotNegative;
import static com.akikanellis.kata01.utils.Preconditions.checkNotNull;

public final class Price {
    public static final Price ZERO = Price.of(BigDecimal.ZERO);
    public static final Price ONE = Price.of(BigDecimal.ONE);

    private final BigDecimal amount;

    private Price(BigDecimal amount) { this.amount = checkNotNull(amount); }

    public static Price of(long amount) {
        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        return of(bigDecimalAmount);
    }

    public static Price of(BigDecimal amount) { return new Price(amount); }

    public Price add(Price other) {
        BigDecimal newAmount = amount.add(other.amount);
        return new Price(newAmount);
    }

    public Price multiplyBy(long multiplier) {
        checkNotNegative(multiplier);

        BigDecimal newAmount = amount.multiply(BigDecimal.valueOf(multiplier));
        return new Price(newAmount);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price other = (Price) o;
        return Objects.equals(amount, other.amount);
    }

    @Override public int hashCode() { return Objects.hashCode(amount); }
}
