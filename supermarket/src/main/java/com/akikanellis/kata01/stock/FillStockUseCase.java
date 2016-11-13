package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;

import static com.akikanellis.kata01.utils.Preconditions.checkNotNegative;

/**
 * Increases the quantity in the stock of the specified item by the specified quantity.
 */
public class FillStockUseCase {
    private final StockRepository stock;
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExists;

    public FillStockUseCase(StockRepository stock, AddNewItemIfNotExistsUseCase addNewItemIfNotExists) {
        this.stock = stock;
        this.addNewItemIfNotExists = addNewItemIfNotExists;
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
