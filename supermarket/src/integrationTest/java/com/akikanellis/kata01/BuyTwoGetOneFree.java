package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.price.Price;

/**
 * An example offer strategy used for testing where when we buy 2 items, we get an extra one for free. Price wise this
 * means that for every 3 items we subtract an equivalent to the item's price.
 */
class BuyTwoGetOneFree extends QuantityBasedOfferStrategy {

    private BuyTwoGetOneFree(long id, String description, Item applicableItem, Price discount) {
        super(id, description, applicableItem, 3, discount);
    }

    static BuyTwoGetOneFree create(long id, Item applicableItem) {
        Price discount = applicableItem.price().negate();
        String description = String.format("Buy 2 %s, get 1 free", applicableItem.name());

        return new BuyTwoGetOneFree(id, description, applicableItem, discount);
    }
}
