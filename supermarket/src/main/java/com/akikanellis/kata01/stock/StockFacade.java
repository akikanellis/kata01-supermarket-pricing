package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.price.Price;

public class StockFacade {
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase;
    private final FillStockUseCase fillStockUseCase;
    private final ReduceStockUseCase reduceStock;
    private final GetStockUseCase getStock;
    private final AddOfferStrategyUseCase addOfferStrategy;
    private final RemoveOfferStrategyUseCase removeOfferStrategy;
    private final GetActiveOfferStrategiesUseCase getActiveOfferStrategies;
    private final GetStockValueBeforeOffersUseCase getStockValueBeforeOffers;
    private final GetOffersValueUseCase getOffersValue;

    public StockFacade(AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase,
                       FillStockUseCase fillStockUseCase,
                       ReduceStockUseCase reduceStock,
                       GetStockUseCase getStock, AddOfferStrategyUseCase addOfferStrategy,
                       RemoveOfferStrategyUseCase removeOfferStrategy,
                       GetActiveOfferStrategiesUseCase getActiveOfferStrategies,
                       GetStockValueBeforeOffersUseCase getStockValueBeforeOffers,
                       GetOffersValueUseCase getOffersValue) {
        this.addNewItemIfNotExistsUseCase = addNewItemIfNotExistsUseCase;
        this.fillStockUseCase = fillStockUseCase;
        this.reduceStock = reduceStock;
        this.getStock = getStock;
        this.addOfferStrategy = addOfferStrategy;
        this.removeOfferStrategy = removeOfferStrategy;
        this.getActiveOfferStrategies = getActiveOfferStrategies;
        this.getStockValueBeforeOffers = getStockValueBeforeOffers;
        this.getOffersValue = getOffersValue;
    }

    public void addNewItem(Item item) { addNewItemIfNotExistsUseCase.execute(item); }

    public void fillStock(Item item, int quantity) { fillStockUseCase.execute(item, quantity); }

    public void reduceStock(Item item, int quantity) { reduceStock.execute(item, quantity); }

    public Items getStock() { return getStock.execute(); }

    public void addOfferStrategy(OfferStrategy offerStrategy) { addOfferStrategy.execute(offerStrategy); }

    public void removeOfferStrategy(OfferStrategy offerStrategy) { removeOfferStrategy.execute(offerStrategy); }

    public OfferStrategies getActiveOfferStrategies() { return getActiveOfferStrategies.execute(); }

    public Price getStockValueBeforeOffers() { return getStockValueBeforeOffers.execute(); }

    public Price getOffersValue() { return getOffersValue.execute(); }
}
