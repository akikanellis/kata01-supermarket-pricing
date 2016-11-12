package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;

import static com.akikanellis.kata01.utils.Preconditions.checkNotNegative;

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
        stock.replaceQuantity(item, newQuantity);
    }

    private int calculateNewQuantity(Item item, int quantity) {
        int currentQuantity = stock.getQuantity(item);
        return currentQuantity + quantity;
    }
}
