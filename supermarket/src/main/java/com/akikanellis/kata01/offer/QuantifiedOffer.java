package com.akikanellis.kata01.offer;

import com.akikanellis.kata01.price.Price;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class QuantifiedOffer {

    public static QuantifiedOffer create(Offer offer, int quantity) {
        Price totalPrice = offer.price().multiplyBy(quantity);
        return new AutoValue_QuantifiedOffer(offer, quantity, totalPrice);
    }

    public abstract Offer offer();

    public abstract int quantity();

    public abstract Price totalPrice();
}
