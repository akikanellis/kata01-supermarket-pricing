package com.akikanellis.kata01.offer;

import com.akikanellis.kata01.price.Price;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class OfferTest {

    @Test public void creating_withNegativePrice_creates() { Offer.create("offer", Price.of(-1)); }

    @Test public void creating_withZeroPrice_creates() { Offer.create("offer", Price.ZERO); }

    @Test public void creating_withPositivePrice_throwsException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Offer.create("offer", Price.ONE));
    }
}