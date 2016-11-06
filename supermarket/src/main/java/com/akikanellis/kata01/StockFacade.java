package com.akikanellis.kata01;

public class StockFacade {
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase;
    private final FillStockUseCase fillStockUseCase;
    private final ReduceStockUseCase reduceStock;
    private final GetStockUseCase getStock;

    public StockFacade(AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase,
                       FillStockUseCase fillStockUseCase,
                       ReduceStockUseCase reduceStock,
                       GetStockUseCase getStock) {
        this.addNewItemIfNotExistsUseCase = addNewItemIfNotExistsUseCase;
        this.fillStockUseCase = fillStockUseCase;
        this.reduceStock = reduceStock;
        this.getStock = getStock;
    }

    public void addNewItem(Item item) { addNewItemIfNotExistsUseCase.execute(item); }

    public void fillStock(Item item, int quantity) { fillStockUseCase.execute(item, quantity); }

    public void reduceStock(Item item, int quantity) { reduceStock.execute(item, quantity); }

    public Items getStock() { return getStock.execute(); }
}
