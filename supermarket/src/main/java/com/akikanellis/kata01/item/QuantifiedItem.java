package com.akikanellis.kata01.item;

import com.akikanellis.kata01.price.Price;
import com.google.auto.value.AutoValue;

/**
 * Represents an item with a quantity of it.
 */
@AutoValue
public abstract class QuantifiedItem {

    public static QuantifiedItem create(Item item, int quantity) {
        Price totalPrice = item.price().multiplyBy(quantity);
        return new AutoValue_QuantifiedItem(item, quantity, totalPrice);
    }

    /**
     * @return the item that is represented
     */
    public abstract Item item();

    /**
     * @return the quantity of the item
     */
    public abstract int quantity();

    /**
     * The total price is calculated by multiplying the item's base price by the given quantity.
     *
     * @return the total price of the item
     */
    public abstract Price totalPrice();
}
