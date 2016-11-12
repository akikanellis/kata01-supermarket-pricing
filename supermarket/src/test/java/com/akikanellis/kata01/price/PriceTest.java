package com.akikanellis.kata01.price;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PriceTest {

    @Test public void creating_withNullAmount_throwsException() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> Price.of(null));
    }

    @Test public void equals_isCorrect() { EqualsVerifier.forClass(Price.class).verify(); }

    @Test public void multiplyBy_withPositiveNumber_returnsMultipliedPrice() {
        Price price = Price.of(500);
        Price expectedPrice = Price.of(1500);

        Price actualPrice = price.multiplyBy(3);

        assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test public void multiplyBy_withZero_returnsZero() {
        Price price = Price.ONE;
        Price expectedPrice = Price.ZERO;

        Price actualPrice = price.multiplyBy(0);

        assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test public void multiplyBy_withNegativeNumber_throwsException() {
        Price price = Price.ONE;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> price.multiplyBy(-5));
    }
}