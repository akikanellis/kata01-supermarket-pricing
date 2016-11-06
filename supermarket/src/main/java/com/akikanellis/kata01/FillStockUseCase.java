package com.akikanellis.kata01;

public class FillStockUseCase {
    private final StockRepository stock;
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExists;

    public FillStockUseCase(StockRepository stock, AddNewItemIfNotExistsUseCase addNewItemIfNotExists) {
        this.addNewItemIfNotExists = addNewItemIfNotExists;
        this.stock = stock;
    }

    public void execute(Item item, int quantity) {
        addNewItemIfNotExists.execute(item);

        int currentQuantity = stock.getQuantity(item);
        int newQuantity = currentQuantity + quantity;
        try {
            stock.addQuantity(item, newQuantity);
        } catch (ItemDoesNotExistException e) {
            e.printStackTrace();
        }
    }
}
