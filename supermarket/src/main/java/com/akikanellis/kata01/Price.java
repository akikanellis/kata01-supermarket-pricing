package com.akikanellis.kata01;

import java.math.BigDecimal;
import java.util.Objects;

import static com.akikanellis.kata01.utils.Preconditions.checkNotNegative;
import static com.akikanellis.kata01.utils.Preconditions.checkNotNull;

public final class Price {
    public static final Price ZERO = new Price(BigDecimal.ZERO);
    public static final Price ONE = new Price(BigDecimal.ONE);

    private final BigDecimal amount;

    private Price(BigDecimal amount) {
        this.amount = checkNotNull(amount);
    }

    public static Price from(int amount) {
        BigDecimal bigDecimalAmount = new BigDecimal(amount);
        return from(bigDecimalAmount);
    }

    public static Price from(BigDecimal amount) { return new Price(amount); }

    public Price multiplyBy(int multiplier) {
        checkNotNegative(multiplier);

        BigDecimal newAmount = amount.multiply(new BigDecimal(multiplier));
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
