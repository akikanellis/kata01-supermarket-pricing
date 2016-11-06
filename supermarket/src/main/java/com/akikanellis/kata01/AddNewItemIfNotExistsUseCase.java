package com.akikanellis.kata01;

public class AddNewItemIfNotExistsUseCase {
    private final StockRepository stock;

    public AddNewItemIfNotExistsUseCase(StockRepository stock) { this.stock = stock; }

    public void execute(Item item) {
        if (!stock.contains(item)) {
            stock.create(item);
        }
    }
}
