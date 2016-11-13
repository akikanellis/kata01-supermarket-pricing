package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.price.Price;

/**
 * A facade for easily accessing all the functionality regarding the stock management.
 */
public class StockFacade {
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExists;
    private final FindItemByBarcodeUseCase findItemByBarcode;
    private final FillStockUseCase fillStock;
    private final ReduceStockUseCase reduceStock;
    private final GetStockUseCase getStock;
    private final FindOfferStrategyByIdUseCase findOfferStrategyById;
    private final AddOfferStrategyUseCase addOfferStrategy;
    private final RemoveOfferStrategyUseCase removeOfferStrategy;
    private final GetActiveOfferStrategiesUseCase getActiveOfferStrategies;
    private final GetApplicableOffersUseCase getApplicableOffers;
    private final GetOffersValueUseCase getOffersValue;
    private final GetStockValueBeforeOffersUseCase getStockValueBeforeOffers;
    private final GetStockValueAfterOffersUseCase getStockValueAfterOffers;

    public StockFacade(AddNewItemIfNotExistsUseCase addNewItemIfNotExists, FindItemByBarcodeUseCase findItemByBarcode,
                       FillStockUseCase fillStock, ReduceStockUseCase reduceStock, GetStockUseCase getStock,
                       FindOfferStrategyByIdUseCase findOfferStrategyById, AddOfferStrategyUseCase addOfferStrategy,
                       RemoveOfferStrategyUseCase removeOfferStrategy,
                       GetActiveOfferStrategiesUseCase getActiveOfferStrategies,
                       GetApplicableOffersUseCase getApplicableOffers, GetOffersValueUseCase getOffersValue,
                       GetStockValueBeforeOffersUseCase getStockValueBeforeOffers,
                       GetStockValueAfterOffersUseCase getStockValueAfterOffers) {
        this.addNewItemIfNotExists = addNewItemIfNotExists;
        this.findItemByBarcode = findItemByBarcode;
        this.fillStock = fillStock;
        this.reduceStock = reduceStock;
        this.getStock = getStock;
        this.findOfferStrategyById = findOfferStrategyById;
        this.addOfferStrategy = addOfferStrategy;
        this.removeOfferStrategy = removeOfferStrategy;
        this.getActiveOfferStrategies = getActiveOfferStrategies;
        this.getApplicableOffers = getApplicableOffers;
        this.getOffersValue = getOffersValue;
        this.getStockValueBeforeOffers = getStockValueBeforeOffers;
        this.getStockValueAfterOffers = getStockValueAfterOffers;
    }

    /**
     * Adds a new item to the stock.
     *
     * @param item the item to add
     */
    public void addNewItem(Item item) { addNewItemIfNotExists.execute(item); }

    /**
     * Increases an item's quantity by the given one.
     *
     * @param barcode  the barcode of the item
     * @param quantity the quantity to increase by
     */
    public void fillStock(long barcode, int quantity) {
        Item item = findItemByBarcode.execute(barcode);
        fillStock.execute(item, quantity);
    }

    /**
     * Reduces an item's quantity by the given one.
     *
     * @param barcode  the barcode of the item
     * @param quantity the quantity to decrease by
     */
    public void reduceStock(long barcode, int quantity) {
        Item item = findItemByBarcode.execute(barcode);
        reduceStock.execute(item, quantity);
    }

    /**
     * Gives access to the whole item stock.
     *
     * @return all the available items in the stock
     */
    public Items getStock() { return getStock.execute(); }

    /**
     * Adds a new offer strategy.
     *
     * @param offerStrategy the strategy to add
     */
    public void addOfferStrategy(OfferStrategy offerStrategy) { addOfferStrategy.execute(offerStrategy); }

    /**
     * Removes an offer strategy.
     *
     * @param id the id od the strategy to remove
     */
    public void removeOfferStrategy(long id) {
        OfferStrategy offerStrategy = findOfferStrategyById.execute(id);
        removeOfferStrategy.execute(offerStrategy);
    }

    /**
     * Returns all the currently active offer strategies.
     *
     * @return the currently active offer strategies
     */
    public OfferStrategies getActiveOfferStrategies() { return getActiveOfferStrategies.execute(); }

    /**
     * Returns all the offers that apply to the stock.
     *
     * @return the applicable offers
     */
    public Offers getApplicableOffers() { return getApplicableOffers.execute(); }

    /**
     * Returns the total value of the offers.
     *
     * @return the total value of the offers
     */
    public Price getOffersValue() { return getOffersValue.execute(); }

    /**
     * Returns the total value of the stock before applying the offers.
     *
     * @return the total value of the stock before the offers
     */
    public Price getStockValueBeforeOffers() { return getStockValueBeforeOffers.execute(); }

    /**
     * Returns the total value of the stock after applying the offers.
     *
     * @return the total value of the stock after the offers
     */
    public Price getStockValueAfterOffers() { return getStockValueAfterOffers.execute(); }
}
