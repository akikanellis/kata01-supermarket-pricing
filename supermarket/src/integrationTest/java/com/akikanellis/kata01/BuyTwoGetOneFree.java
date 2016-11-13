package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.price.Price;

public class BuyTwoGetOneFree extends QuantityBasedOfferStrategy {
    private BuyTwoGetOneFree(long id, String description, Item applicableItem, Price discount) {
        super(id, description, applicableItem, 3, discount);
    }

    public static BuyTwoGetOneFree create(long id, Item applicableItem) {
        Price discount = applicableItem.price();
        String description = String.format("Buy 2 %s, get 1 free", applicableItem.name());

        return new BuyTwoGetOneFree(id, description, applicableItem, discount);
    }
}
