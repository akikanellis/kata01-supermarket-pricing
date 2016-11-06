package com.akikanellis.kata01;

public class AddNewItemUseCase {
    private final StockRepository stock;

    public AddNewItemUseCase(StockRepository stock) { this.stock = stock; }

    public void execute(Item item) { stock.create(item); }
}
