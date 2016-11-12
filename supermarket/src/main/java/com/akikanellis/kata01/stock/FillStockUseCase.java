package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.Item;

import static com.akikanellis.kata01.Preconditions.checkNotNegative;

public class FillStockUseCase {
    private final InMemoryStockRepository stock;
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExists;

    public FillStockUseCase(InMemoryStockRepository stock, AddNewItemIfNotExistsUseCase addNewItemIfNotExists) {
        this.addNewItemIfNotExists = addNewItemIfNotExists;
        this.stock = stock;
    }

    public void execute(Item item, int quantity) {
        checkNotNegative(quantity);
        addNewItemIfNotExists.execute(item);

        int newQuantity = calculateNewQuantity(item, quantity);
        stock.replaceQuantity(item, newQuantity);
    }

    private int calculateNewQuantity(Item item, int quantity) {
        int currentQuantity = stock.getQuantity(item);
        return currentQuantity + quantity;
    }
}
