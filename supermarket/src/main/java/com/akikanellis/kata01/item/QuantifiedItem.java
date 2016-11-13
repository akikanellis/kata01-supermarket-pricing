package com.akikanellis.kata01.item;

import com.akikanellis.kata01.price.Price;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class QuantifiedItem {

    public static QuantifiedItem create(Item item, int quantity) {
        Price totalPrice = item.price().multiplyBy(quantity);
        return new AutoValue_QuantifiedItem(item, quantity, totalPrice);
    }

    public abstract Item item();

    public abstract int quantity();

    public abstract Price totalPrice();
}
