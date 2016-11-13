package com.akikanellis.kata01.offer;

import com.akikanellis.kata01.price.Price;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuantifiedOfferTest {

    @Test public void totalPrice_isMultiplicationOfPriceAndQuantity() {
        Offer offer = Offer.create("offer", Price.of(-20));

        QuantifiedOffer quantifiedItem = QuantifiedOffer.create(offer, 30);

        assertThat(quantifiedItem.totalPrice()).isEqualTo(Price.of(-600));
    }
}