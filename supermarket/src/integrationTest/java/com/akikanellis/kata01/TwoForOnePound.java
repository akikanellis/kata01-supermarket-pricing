package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.price.Price;

public class TwoForOnePound extends QuantityBasedOfferStrategy {
    private TwoForOnePound(long id, String description, Item applicableItem, Price discount) {
        super(id, description, applicableItem, 2, discount);
    }

    public static TwoForOnePound create(long id, Item applicableItem) {
        Price pricePerTwo = applicableItem.price().multiplyBy(2);
        Price discount = Price.of(100).subtract(pricePerTwo);
        String description = String.format("Buy 2 %s, for £1", applicableItem.name());

        return new TwoForOnePound(id, description, applicableItem, discount);
    }
}
