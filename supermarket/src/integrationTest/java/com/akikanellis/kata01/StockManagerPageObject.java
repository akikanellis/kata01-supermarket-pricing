package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.price.Price;
import com.akikanellis.kata01.stock.StockFacade;

public class StockManagerPageObject {
    private final StockFacade stockFacade;
    private final Item apple;
    private final Item cheese;
    private final Item beans;
    private OfferStrategy appleOfferStrategy;
    private OfferStrategy beansOfferStrategy;
    private OfferStrategy tenPercentOffEverythingOfferStrategy;

    public StockManagerPageObject(StockFacade stockFacade) {
        this.apple = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.of(40))
                .build();
        this.cheese = Item.builder()
                .barcode(2)
                .name("Cheese")
                .price(Price.of(0.005))
                .build();
        this.beans = Item.builder()
                .barcode(3)
                .name("Beans")
                .price(Price.of(120))
                .build();

        appleOfferStrategy = BuyTwoGetOneFree.create(1, apple);
        beansOfferStrategy = TwoForOnePound.create(2, beans);
        tenPercentOffEverythingOfferStrategy = TenPercentOffEverything.create(3);

        this.stockFacade = stockFacade;
    }

    public Item getApple() { return apple; }

    public Item getCheese() { return cheese; }

    public Item getBeans() { return beans; }

    public OfferStrategy getAppleOfferStrategy() { return appleOfferStrategy; }

    public OfferStrategy getBeansOfferStrategy() { return beansOfferStrategy; }

    public OfferStrategy getTenPercentOffEverythingOfferStrategy() { return tenPercentOffEverythingOfferStrategy; }

    public void createAppleBeansAndCheese() {
        createApple();
        createBeans();
        createCheese();
    }

    public void createApple() { stockFacade.addNewItem(apple); }

    public void increaseAppleQuantity(int quantity) { stockFacade.fillStock(apple.barcode(), quantity); }

    public void decreaseAppleQuantity(int quantity) { stockFacade.reduceStock(apple.barcode(), quantity); }

    public void createCheese() { stockFacade.addNewItem(cheese); }

    public void increaseCheeseQuantity(int quantity) { stockFacade.fillStock(cheese.barcode(), quantity); }

    public void createBeans() { stockFacade.addNewItem(beans); }

    public void increaseBeansQuantity(int quantity) { stockFacade.fillStock(beans.barcode(), quantity); }

    public Items getStock() { return stockFacade.getStock(); }

    public void createThreeOffers() {
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
}
