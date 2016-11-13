package com.akikanellis.kata01.price;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PriceTest {

    @Test public void creating_withNullAmount_throwsException() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> Price.of(null));
    }

    @Test public void equals_isCorrect() { EqualsVerifier.forClass(Price.class).verify(); }

    @Test public void ofDouble_returnsAsPrice() {
        Price expectedPrice = Price.of(new BigDecimal("13.4"));

        Price actualPrice = Price.of(13.4);

        assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test public void isPositive_withPositiveNumber_returnsTrue() { assertThat(Price.of(15).isPositive()).isTrue(); }

    @Test public void isPositive_withNegativeNumber_returnsFalse() { assertThat(Price.of(-3).isPositive()).isFalse(); }

    @Test public void isPositive_withZero_returnsFalse() { assertThat(Price.ZERO.isPositive()).isFalse(); }

    @Test public void isNegative_withPositiveNumber_returnsFalse() { assertThat(Price.of(15).isNegative()).isFalse(); }

    @Test public void isNegative_withNegativeNumber_returnsTrue() { assertThat(Price.of(-3).isNegative()).isTrue(); }

    @Test public void isNegative_withZero_returnsFalse() { assertThat(Price.ZERO.isNegative()).isFalse(); }

    @Test public void isZero_withPositiveNumber_returnsFalse() { assertThat(Price.of(15).isZero()).isFalse(); }

    @Test public void isZero_withNegativeNumber_returnsFalse() { assertThat(Price.of(-3).isZero()).isFalse(); }

    @Test public void isZero_withZero_returnsTrue() { assertThat(Price.ZERO.isZero()).isTrue(); }

    @Test public void negating_withPositiveNumber_returnsNegative() {
        Price price = Price.of(200);

        Price negated = price.negate();

        assertThat(negated).isEqualTo(Price.of(-200));
    }

    @Test public void negating_withNegativeNumber_returnsPositive() {
        Price price = Price.of(-200);

        Price negated = price.negate();

        assertThat(negated).isEqualTo(Price.of(200));
    }

    @Test public void negating_withZero_returnsZero() {
        Price price = Price.ZERO;

        Price negated = price.negate();

        assertThat(negated).isEqualTo(Price.ZERO);
    }

    @Test public void adding_withPositiveNumber_returnsAddedPrice() {
        Price price = Price.of(500);

        Price newPrice = price.add(Price.of(700));

        assertThat(newPrice).isEqualTo(Price.of(1200));
    }

    @Test public void adding_withNegativeNumber_returnsAddedPrice() {
        Price price = Price.of(500);

        Price newPrice = price.add(Price.of(-300));

        assertThat(newPrice).isEqualTo(Price.of(200));
    }

    @Test public void adding_withZero_returnsSamePrice() {
        Price expectedPrice = Price.of(500);

        Price actualPrice = expectedPrice.add(Price.ZERO);

        assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test public void subtracting_withPositiveNumber_returnsSubtractedPrice() {
        Price price = Price.of(500);

        Price newPrice = price.subtract(Price.of(200));

        assertThat(newPrice).isEqualTo(Price.of(300));
    }

    @Test public void subtracting_withNegativeNumber_returnsSubtractedPrice() {
        Price price = Price.of(500);

        Price newPrice = price.subtract(Price.of(-300));

        assertThat(newPrice).isEqualTo(Price.of(800));
    }

    @Test public void subtracting_withZero_returnsSamePrice() {
        Price expectedPrice = Price.of(500);

        Price actualPrice = expectedPrice.add(Price.ZERO);

        assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test public void multiplyBy_withPositiveNumber_returnsMultipliedPrice() {
        Price price = Price.of(500);
        Price expectedPrice = Price.of(1500);

        Price actualPrice = price.multiplyBy(3);

        assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test public void multiplyBy_withNegativeNumber_throwsException() {
        Price price = Price.ONE;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> price.multiplyBy(-5));
    }

    @Test public void multiplyBy_withZero_returnsZero() {
        Price price = Price.ONE;

        Price newPrice = price.multiplyBy(0);

        assertThat(newPrice).isEqualTo(Price.ZERO);
    }
}