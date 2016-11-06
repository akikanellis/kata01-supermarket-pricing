package com.akikanellis.kata01;

import java.math.BigDecimal;
import java.util.Objects;

import static com.akikanellis.kata01.Preconditions.checkNotNull;

public final class Price {
    public static final Price ZERO = new Price(BigDecimal.ZERO);
    public static final Price ONE = new Price(BigDecimal.ONE);

    private final BigDecimal amount;

    public Price(BigDecimal amount) {
        this.amount = checkNotNull(amount);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price other = (Price) o;
        return Objects.equals(amount, other.amount);
    }

    @Override public int hashCode() { return Objects.hashCode(amount); }
}
