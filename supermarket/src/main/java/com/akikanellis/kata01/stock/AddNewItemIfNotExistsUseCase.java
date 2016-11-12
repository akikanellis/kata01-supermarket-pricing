package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.Item;

public class AddNewItemIfNotExistsUseCase {
    private final InMemoryStockRepository stock;

    public AddNewItemIfNotExistsUseCase(InMemoryStockRepository stock) { this.stock = stock; }

    public void execute(Item item) {
        if (!stock.contains(item)) {
            stock.create(item);
        }
    }
}
