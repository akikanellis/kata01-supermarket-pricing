package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;

/**
 * Adds a new item to the stock repository only if it does not already exist.
 */
public class AddNewItemIfNotExistsUseCase {
    private final StockRepository stock;

    public AddNewItemIfNotExistsUseCase(StockRepository stock) { this.stock = stock; }

    public void execute(Item item) {
        if (!stock.contains(item)) {
            stock.create(item);
        }
    }
}
