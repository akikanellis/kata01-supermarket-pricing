package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.price.Price;
import com.akikanellis.kata01.stock.StockFacade;

public class StockManagerPageObject {
    private final StockFacade stockFacade;
    private final Item.Builder defaultAppleBuilder;
    private final Item.Builder defaultCheeseBuilder;
    private final Item.Builder defaultBeansBuilder;
    private final Item defaultApple;
    private final Item defaultCheese;
    private final Item defaultBeans;
    private OfferStrategy appleOfferStrategy;
    private OfferStrategy beansOfferStrategy;
    private OfferStrategy tenPercentOffEverythingOfferStrategy;

    public StockManagerPageObject(StockFacade stockFacade) {
        this.defaultAppleBuilder = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.of(40));
        this.defaultApple = defaultAppleBuilder.build();
        this.defaultCheeseBuilder = Item.builder()
                .barcode(2)
                .name("Cheese")
                .price(Price.of(0.5));
        this.defaultCheese = defaultCheeseBuilder.build();
        this.defaultBeansBuilder = Item.builder()
                .barcode(3)
                .name("Beans")
                .price(Price.of(80));
        this.defaultBeans = defaultBeansBuilder.build();

        appleOfferStrategy = BuyTwoGetOneFree.create(1, defaultApple);
        beansOfferStrategy = TwoForOnePound.create(2, defaultBeans);
        tenPercentOffEverythingOfferStrategy = TenPercentOffEverything.create(3);

        this.stockFacade = stockFacade;
    }

    public Item getDefaultApple() { return defaultApple; }

    public Item getCheese() { return defaultCheese; }

    public Item getBeans() { return defaultBeans; }

    public OfferStrategy getAppleOfferStrategy() { return appleOfferStrategy; }

    public OfferStrategy getBeansOfferStrategy() { return beansOfferStrategy; }

    public OfferStrategy getTenPercentOffEverythingOfferStrategy() { return tenPercentOffEverythingOfferStrategy; }

    public void createAppleBeansAndCheese() {
        createApple();
        createBeans();
        createCheese();
    }

    public void createApple() { stockFacade.addNewItem(defaultApple); }

    public void createAppleWithQuantity() { stockFacade.addNewItem(defaultApple); }

    public void increaseAppleQuantity(int quantity) { stockFacade.fillStock(defaultApple.barcode(), quantity); }

    public void decreaseAppleQuantity(int quantity) { stockFacade.reduceStock(defaultApple.barcode(), quantity); }

    public void createCheese() { stockFacade.addNewItem(defaultCheese); }

    public void increaseCheeseQuantity(int quantity) { stockFacade.fillStock(defaultCheese.barcode(), quantity); }

    public void createBeans() { stockFacade.addNewItem(defaultBeans); }

    public void increaseBeansQuantity(int quantity) { stockFacade.fillStock(defaultBeans.barcode(), quantity); }

    public Items getStock() { return stockFacade.getStock(); }

    public void createAllOffers() {
        createAppleOffer();
        createBeansOffer();
        createTenPercentOffEverythingOffer();
    }

    public void createAppleOffer() { stockFacade.addOfferStrategy(appleOfferStrategy); }

    public void createBeansOffer() { stockFacade.addOfferStrategy(beansOfferStrategy); }

    public void createTenPercentOffEverythingOffer() {
        stockFacade.addOfferStrategy(tenPercentOffEverythingOfferStrategy);
    }

    public OfferStrategies getActiveOfferStrategies() { return stockFacade.getActiveOfferStrategies(); }

    public void removeAppleOffer() { stockFacade.removeOfferStrategy(appleOfferStrategy.id()); }

    public Offers getApplicableOffers() { return stockFacade.getApplicableOffers(); }

    public Price getOffersValue() { return stockFacade.getOffersValue(); }

    public Price getStockValueBeforeOffers() { return stockFacade.getStockValueBeforeOffers(); }
}
