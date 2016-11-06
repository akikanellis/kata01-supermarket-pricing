package com.akikanellis.kata01;

public class StockFacade {
    private final AddNewItemUseCase addNewItemUseCase;

    public StockFacade(AddNewItemUseCase addNewItemUseCase) {
        this.addNewItemUseCase = addNewItemUseCase;
    }

    public void addNewItem(Item item) { addNewItemUseCase.execute(item); }
}
