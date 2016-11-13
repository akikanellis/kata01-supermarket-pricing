package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.price.Price;
import com.akikanellis.kata01.stock.StockFacade;

/**
 * A simulation of a Stock Manager user through the usage of the Page Object Pattern.
 */
public class StockManagerPageObject {
    private final StockFacade stockFacade;
    private final Item apple;
    private final Item beans;
    private final Item cheese;
    private OfferStrategy appleOfferStrategy;
    private OfferStrategy beansOfferStrategy;
    private OfferStrategy tenPercentOffEverythingOfferStrategy;

    public StockManagerPageObject(StockFacade stockFacade) {
        this.apple = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.of(40))
                .build();
        this.beans = Item.builder()
                .barcode(2)
                .name("Beans")
                .price(Price.of(80))
                .build();
        this.cheese = Item.builder()
                .barcode(3)
                .name("Cheese")
                .price(Price.of(0.5))
                .build();

        this.appleOfferStrategy = BuyTwoGetOneFree.create(1, apple);
        this.beansOfferStrategy = TwoForOnePound.create(2, beans);
        this.tenPercentOffEverythingOfferStrategy = TenPercentOffEverything.create(3);

        this.stockFacade = stockFacade;
    }

    public Item apple() { return apple; }

    public Item beans() { return beans; }

    public Item cheese() { return cheese; }

    public OfferStrategy appleOfferStrategy() { return appleOfferStrategy; }

    public OfferStrategy beansOfferStrategy() { return beansOfferStrategy; }

    public OfferStrategy tenPercentOffEverythingOfferStrategy() { return tenPercentOffEverythingOfferStrategy; }

    public void createAndAddAllItems() {
        createAppleBeansAndCheese();
        increaseAppleQuantity(30);
        increaseBeansQuantity(100);
        increaseCheeseQuantity(20000);
    }

    public void createAppleBeansAndCheese() {
        createApple();
        createBeans();
        createCheese();
    }

    public void createApple() { stockFacade.addNewItem(apple); }

    public void increaseAppleQuantity(int quantity) { stockFacade.fillStock(apple.barcode(), quantity); }

    public void decreaseAppleQuantity(int quantity) { stockFacade.reduceStock(apple.barcode(), quantity); }

    public void createBeans() { stockFacade.addNewItem(beans); }

    public void increaseBeansQuantity(int quantity) { stockFacade.fillStock(beans.barcode(), quantity); }

    public void createCheese() { stockFacade.addNewItem(cheese); }

    public void increaseCheeseQuantity(int quantity) { stockFacade.fillStock(cheese.barcode(), quantity); }

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

    public Price getStockValueAfterOffers() { return stockFacade.getStockValueAfterOffers(); }
}
