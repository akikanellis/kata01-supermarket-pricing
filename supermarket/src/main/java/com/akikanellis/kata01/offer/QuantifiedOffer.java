package com.akikanellis.kata01.offer;

import com.akikanellis.kata01.price.Price;
import com.google.auto.value.AutoValue;

/**
 * Represents an offer with a quantity of it.
 */
@AutoValue
public abstract class QuantifiedOffer {

    public static QuantifiedOffer create(Offer offer, int quantity) {
        Price totalPrice = offer.price().multiplyBy(quantity);
        return new AutoValue_QuantifiedOffer(offer, quantity, totalPrice);
    }

    /**
     * @return the offer that is represented
     */
    public abstract Offer offer();

    /**
     * @return the quantity of the offer
     */
    public abstract int quantity();

    /**
     * The total price is calculated by multiplying the offer's base price by the given quantity.
     *
     * @return the total price of the offer
     */
    public abstract Price totalPrice();
}
