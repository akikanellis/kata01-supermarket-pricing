package com.akikanellis.kata01;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class OfferWithQuantity {

    public static OfferWithQuantity create(Offer offer, int quantity) {
        Price totalPrice = offer.price().multiplyBy(quantity);
        return new AutoValue_OfferWithQuantity(offer, quantity, totalPrice);
    }

    public abstract Offer offer();

    public abstract int quantity();

    public abstract Price totalPrice();
}
