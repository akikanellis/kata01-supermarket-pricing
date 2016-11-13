package com.akikanellis.kata01.offer;

import com.akikanellis.kata01.price.Price;
import com.google.auto.value.AutoValue;

import static com.akikanellis.kata01.utils.Preconditions.checkArgument;

/**
 * A representation of an offer. An offer refers to how much price to remove from a value. The price much be
 * non-positive.
 */
@AutoValue
public abstract class Offer {

    public static Offer create(String description, Price price) {
        checkArgument(!price.isPositive(), "Offer price can't be positive. Was [%s]", price);

        return new AutoValue_Offer(description, price);
    }

    /**
     * The description of the {@code Offer}.
     * <p>
     * For example:
     * <p>
     * {@code 2 Apples for £1}
     *
     * @return the description of the {@code Offer}
     */
    public abstract String description();

    /**
     * The price of the {@code Offer}.
     * <p>
     * For example:
     * <p>
     * {@code -20}
     *
     * @return the price of the {@code Offer}
     */
    public abstract Price price();
}
