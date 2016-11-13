package com.akikanellis.kata01.price;

import java.math.BigDecimal;
import java.util.Objects;

import static com.akikanellis.kata01.utils.Preconditions.checkNotNegative;
import static com.akikanellis.kata01.utils.Preconditions.checkNotNull;

/**
 * Represents the price of our items and offers. The price is represented with the minimum viable money figure (pence)
 * and the usage of a {@link java.math.BigDecimal}.
 * <p>
 * For example the {@code Price{amount=200}} represents a price of 200 pence.
 */
public final class Price {
    public static final Price ZERO = Price.of(BigDecimal.ZERO);
    public static final Price ONE = Price.of(BigDecimal.ONE);

    private final BigDecimal amount;

    private Price(BigDecimal amount) { this.amount = checkNotNull(amount).stripTrailingZeros(); }

    /**
     * Translates a {@code long} amount into a {@code Price}.
     *
     * @param amount amount of the {@code Price}.
     * @return a {@code Price} whose amount is {@code amount}.
     */
    public static Price of(long amount) {
        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        return of(bigDecimalAmount);
    }

    /**
     * Translates a {@code BigDecimal} amount into a {@code Price}.
     *
     * @param amount amount of the {@code Price}
     * @return a {@code Price} whose amount is {@code amount}
     */
    public static Price of(BigDecimal amount) { return new Price(amount); }

    /**
     * Translates a {@code double} amount into a {@code Price}.
     *
     * @param amount amount of the {@code Price}
     * @return a {@code Price} whose amount is {@code amount}
     */
    public static Price of(double amount) {
        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        return new Price(bigDecimalAmount);
    }

    /**
     * Returns if a {@code Price} is positive.
     *
     * @return true if positive, false if not
     */
    public boolean isPositive() { return amount.compareTo(BigDecimal.ZERO) == 1; }

    /**
     * Returns if a {@code Price} is negative.
     *
     * @return true if negative, false if not
     */
    public boolean isNegative() { return amount.compareTo(BigDecimal.ZERO) == -1; }

    /**
     * Returns if a {@code Price} is zero.
     *
     * @return true if zero, false if not
     */
    public boolean isZero() { return amount.compareTo(BigDecimal.ZERO) == 0; }

    /**
     * Returns a {@code Price} whose value is {@code (-this)}.
     *
     * @return {@code -this}
     */
    public Price negate() {
        BigDecimal newAmount = amount.negate();
        return new Price(newAmount);
    }

    /**
     * Returns a {@code Price} whose value is {@code (this + other)}.
     *
     * @param other the other {@code Price} to be added to this {@code Price}.
     * @return {@code this + other}
     */
    public Price add(Price other) {
        BigDecimal newAmount = amount.add(other.amount);
        return new Price(newAmount);
    }

    /**
     * Returns a {@code Price} whose value is {@code (this - other)}.
     *
     * @param other the other {@code Price} to be subtracted from this {@code Price}.
     * @return {@code this - other}
     */
    public Price subtract(Price other) {
        BigDecimal newAmount = amount.subtract(other.amount);
        return new Price(newAmount);
    }

    /**
     * Returns a {@code Price} whose value is {@code (this * multiplier)}.
     *
     * @param multiplier the multiplier to be multiplied with this {@code Price}. The multiplier can't be negative
     * @return {@code this * other}
     */
    public Price multiplyBy(long multiplier) {
        checkNotNegative(multiplier);

        BigDecimal newAmount = amount.multiply(BigDecimal.valueOf(multiplier));
        return new Price(newAmount);
    }

    /**
     * Returns a {@code Price} whose value is {@code (this * multiplier)}.
     *
     * @param multiplier the multiplier to be multiplied with this {@code Price}. The multiplier can't be negative
     * @return {@code this * other}
     */
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
