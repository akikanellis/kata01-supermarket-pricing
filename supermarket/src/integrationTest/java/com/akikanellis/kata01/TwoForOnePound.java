package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.price.Price;

/**
 * An example offer strategy used for testing where when we buy 2 items, we pay £1. Price wise this
 * means that for every 2 items we subtract whatever difference is left from £1 (100p).
 */
public class TwoForOnePound extends QuantityBasedOfferStrategy {
    private static Price ONE_HUNDRED_PENCE = Price.of(100);

    private TwoForOnePound(long id, String description, Item applicableItem, Price discount) {
        super(id, description, applicableItem, 2, discount);
    }

    public static TwoForOnePound create(long id, Item applicableItem) {
        Price priceForTwo = applicableItem.price().multiplyBy(2);
        Price discount = ONE_HUNDRED_PENCE.subtract(priceForTwo);
        String description = String.format("Buy 2 %s, for £1", applicableItem.name());

        return new TwoForOnePound(id, description, applicableItem, discount);
    }
}
