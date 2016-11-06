package com.akikanellis.kata01;

import static com.akikanellis.kata01.Preconditions.checkNotNegative;

public class FillStockUseCase {
    private final StockRepository stock;
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExists;

    public FillStockUseCase(StockRepository stock, AddNewItemIfNotExistsUseCase addNewItemIfNotExists) {
        this.addNewItemIfNotExists = addNewItemIfNotExists;
        this.stock = stock;
    }

    public void execute(Item item, int quantity) {
        checkNotNegative(quantity);
        addNewItemIfNotExists.execute(item);

        int newQuantity = calculateNewQuantity(item, quantity);
        stock.addQuantity(item, newQuantity);
    }

    private int calculateNewQuantity(Item item, int quantity) {
        int currentQuantity = stock.getQuantity(item);
        return currentQuantity + quantity;
    }
}
