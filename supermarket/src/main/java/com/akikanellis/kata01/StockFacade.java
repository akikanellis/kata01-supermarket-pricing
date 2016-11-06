package com.akikanellis.kata01;

public class StockFacade {
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase;
    private final FillStockUseCase fillStockUseCase;

    public StockFacade(AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase, FillStockUseCase fillStockUseCase) {
        this.addNewItemIfNotExistsUseCase = addNewItemIfNotExistsUseCase;
        this.fillStockUseCase = fillStockUseCase;
    }

    public void addNewItem(Item item) { addNewItemIfNotExistsUseCase.execute(item); }

    public void fillStock(Item item, int quantity) { fillStockUseCase.execute(item, quantity); }
}
