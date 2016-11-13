package com.akikanellis.kata01.price;

import java.math.BigDecimal;
import java.util.Objects;

import static com.akikanellis.kata01.utils.Preconditions.checkNotNegative;
import static com.akikanellis.kata01.utils.Preconditions.checkNotNull;

public final class Price {
    public static final Price ZERO = Price.of(BigDecimal.ZERO);
    public static final Price ONE = Price.of(BigDecimal.ONE);

    private final BigDecimal amount;

    private Price(BigDecimal amount) { this.amount = checkNotNull(amount).stripTrailingZeros(); }

    public static Price of(long amount) {
        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        return of(bigDecimalAmount);
    }

    public static Price of(BigDecimal amount) { return new Price(amount); }

    public static Price of(double amount) {
        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        return new Price(bigDecimalAmount);
    }

    public Price negate() {
        BigDecimal newAmount = amount.negate();
        return new Price(newAmount);
    }

    public Price add(Price other) {
        BigDecimal newAmount = amount.add(other.amount);
        return new Price(newAmount);
    }

    public Price subtract(Price other) {
        BigDecimal newAmount = this.amount.subtract(other.amount);
        return new Price(newAmount);
    }

    public Price multiplyBy(long multiplier) {
        checkNotNegative(multiplier);

        BigDecimal newAmount = amount.multiply(BigDecimal.valueOf(multiplier));
        return new Price(newAmount);
    }

    public Price multiplyBy(double multiplier) {
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

    @Override public String toString() { return "Price{amount=" + amount + '}'; }

}
