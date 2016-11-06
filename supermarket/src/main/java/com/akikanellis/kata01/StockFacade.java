package com.akikanellis.kata01;

public class StockFacade {
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase;
    private final FillStockUseCase fillStockUseCase;
    private final ReduceStockUseCase reduceStock;
    private final GetStockUseCase getStock;
    private final AddOfferStrategyUseCase addOfferStrategy;
    private final RemoveOfferStrategyUseCase removeOfferStrategy;

    public StockFacade(AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase,
                       FillStockUseCase fillStockUseCase,
                       ReduceStockUseCase reduceStock,
                       GetStockUseCase getStock, AddOfferStrategyUseCase addOfferStrategy,
                       RemoveOfferStrategyUseCase removeOfferStrategy) {
        this.addNewItemIfNotExistsUseCase = addNewItemIfNotExistsUseCase;
        this.fillStockUseCase = fillStockUseCase;
        this.reduceStock = reduceStock;
        this.getStock = getStock;
        this.addOfferStrategy = addOfferStrategy;
        this.removeOfferStrategy = removeOfferStrategy;
    }

    public void addNewItem(Item item) { addNewItemIfNotExistsUseCase.execute(item); }

    public void fillStock(Item item, int quantity) { fillStockUseCase.execute(item, quantity); }

    public void reduceStock(Item item, int quantity) { reduceStock.execute(item, quantity); }

    public Items getStock() { return getStock.execute(); }

    public void addOfferStrategy(OfferStrategy offerStrategy) { addOfferStrategy.execute(offerStrategy); }

    public void removeOfferStrategy(OfferStrategy offerStrategy) { removeOfferStrategy.execute(offerStrategy); }
}
