package com.akikanellis.kata01;

public class StockFacade {
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase;

    public StockFacade(AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase) {
        this.addNewItemIfNotExistsUseCase = addNewItemIfNotExistsUseCase;
    }

    public void addNewItem(Item item) { addNewItemIfNotExistsUseCase.execute(item); }
}
