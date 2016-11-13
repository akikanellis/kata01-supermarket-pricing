package com.akikanellis.kata01;

import com.akikanellis.kata01.item.ItemDoesNotExistException;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.item.QuantifiedItem;
import com.akikanellis.kata01.offer.InMemoryOfferStrategyRepository;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategyDoesNotExistException;
import com.akikanellis.kata01.offer.OfferStrategyRepository;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.price.Price;
import com.akikanellis.kata01.stock.AddNewItemIfNotExistsUseCase;
import com.akikanellis.kata01.stock.AddOfferStrategyUseCase;
import com.akikanellis.kata01.stock.FillStockUseCase;
import com.akikanellis.kata01.stock.FindItemByBarcodeUseCase;
import com.akikanellis.kata01.stock.FindOfferStrategyByIdUseCase;
import com.akikanellis.kata01.stock.GetActiveOfferStrategiesUseCase;
import com.akikanellis.kata01.stock.GetApplicableOffersUseCase;
import com.akikanellis.kata01.stock.GetOffersValueUseCase;
import com.akikanellis.kata01.stock.GetStockUseCase;
import com.akikanellis.kata01.stock.GetStockValueAfterOffersUseCase;
import com.akikanellis.kata01.stock.GetStockValueBeforeOffersUseCase;
import com.akikanellis.kata01.stock.InMemoryStockRepository;
import com.akikanellis.kata01.stock.ReduceStockUseCase;
import com.akikanellis.kata01.stock.RemoveOfferStrategyUseCase;
import com.akikanellis.kata01.stock.StockFacade;
import com.akikanellis.kata01.stock.StockRepository;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StockFacadeIntegrationTest {
    private StockManagerPageObject stockManager;

    @Before public void beforeEach() {
        StockRepository stock = new InMemoryStockRepository();

        AddNewItemIfNotExistsUseCase addNewItemIfNotExists = new AddNewItemIfNotExistsUseCase(stock);
        FindItemByBarcodeUseCase findItemByBarcode = new FindItemByBarcodeUseCase(stock);
        FillStockUseCase fillStock = new FillStockUseCase(stock, addNewItemIfNotExists);
        ReduceStockUseCase reduceStock = new ReduceStockUseCase(stock, addNewItemIfNotExists);
        GetStockUseCase getStock = new GetStockUseCase(stock);

        OfferStrategyRepository offerStrategies = new InMemoryOfferStrategyRepository();

        FindOfferStrategyByIdUseCase findOfferStrategyById = new FindOfferStrategyByIdUseCase(offerStrategies);
        AddOfferStrategyUseCase addOfferStrategy = new AddOfferStrategyUseCase(offerStrategies);
        RemoveOfferStrategyUseCase removeOfferStrategy = new RemoveOfferStrategyUseCase(offerStrategies);
        GetActiveOfferStrategiesUseCase getActiveOfferStrategies = new GetActiveOfferStrategiesUseCase(offerStrategies);
        GetApplicableOffersUseCase getApplicableOffers = new GetApplicableOffersUseCase(stock, offerStrategies);
        GetOffersValueUseCase getOffersValue = new GetOffersValueUseCase(getApplicableOffers);
        GetStockValueBeforeOffersUseCase getStockValueBeforeOffers = new GetStockValueBeforeOffersUseCase(getStock);
        GetStockValueAfterOffersUseCase getStockValueAfterOffers
                = new GetStockValueAfterOffersUseCase(getStockValueBeforeOffers, getOffersValue);

        StockFacade stockFacade = new StockFacade(addNewItemIfNotExists, findItemByBarcode, fillStock, reduceStock,
                getStock, findOfferStrategyById, addOfferStrategy, removeOfferStrategy, getActiveOfferStrategies,
                getApplicableOffers, getOffersValue, getStockValueBeforeOffers, getStockValueAfterOffers);

        stockManager = new StockManagerPageObject(stockFacade);
    }

    @Test public void creatingItems_withDifferentBarcodes_addsThemToTheRepository() {
        stockManager.createAppleBeansAndCheese();

        assertThat(stockManager.getStock().asItemsList())
                .containsOnly(stockManager.getApple(), stockManager.getCheese(), stockManager.getBeans());
    }

    @Test public void creatingItem_thatAlreadyExists_doesNothing() {
        stockManager.createApple();

        stockManager.createApple();

        assertThat(stockManager.getStock().asItemsList()).hasSize(1);
    }

    @Test public void fillingStock_ofExistingItem_fillsStock() {
        stockManager.createApple();
        stockManager.increaseAppleQuantity(50);
        QuantifiedItem expectedQuantifiedApple = QuantifiedItem.create(stockManager.getApple(), 120);

        stockManager.increaseAppleQuantity(70);

        assertThat(stockManager.getStock().asList()).containsExactly(expectedQuantifiedApple);
    }

    @Test public void fillingStock_ofNotExistingItem_throwsException() {
        assertThatExceptionOfType(ItemDoesNotExistException.class)
                .isThrownBy(() -> stockManager.increaseAppleQuantity(70));
    }

    @Test public void reducingStock_ofExistingItem_reducesStock() {
        stockManager.createApple();
        stockManager.increaseAppleQuantity(50);
        QuantifiedItem expectedQuantifiedApple = QuantifiedItem.create(stockManager.getApple(), 20);

        stockManager.decreaseAppleQuantity(30);

        assertThat(stockManager.getStock().asList()).containsExactly(expectedQuantifiedApple);
    }

    @Test public void reducingStock_ofNotExistingItem_throwsException() {
        assertThatExceptionOfType(ItemDoesNotExistException.class)
                .isThrownBy(() -> stockManager.decreaseAppleQuantity(30));
    }

    @Test public void gettingStock_withoutItems_returnsEmptyStock() {
        Items stock = stockManager.getStock();

        assertThat(stock.isEmpty()).isTrue();
    }

    @Test public void creatingOfferStrategies_withDifferentIds_addsThem() {
        stockManager.createAllOffers();

        assertThat(stockManager.getActiveOfferStrategies().asSet())
                .containsOnly(
                        stockManager.getAppleOfferStrategy(),
                        stockManager.getBeansOfferStrategy(),
                        stockManager.getTenPercentOffEverythingOfferStrategy());
    }

    @Test public void creatingOfferStrategies_withSameIds_replacesThem() {
        stockManager.createAppleOffer();

        stockManager.createAppleOffer();

        assertThat(stockManager.getActiveOfferStrategies().asSet())
                .containsOnly(stockManager.getAppleOfferStrategy());
    }

    @Test public void removingOfferStrategy_withExistingStrategy_removesIt() {
        stockManager.createAppleOffer();

        stockManager.removeAppleOffer();

        assertThat(stockManager.getActiveOfferStrategies().asSet()).isEmpty();
    }

    @Test public void removingOfferStrategy_withNoExistingStrategy_throwsException() {
        assertThatExceptionOfType(OfferStrategyDoesNotExistException.class)
                .isThrownBy(() -> stockManager.removeAppleOffer());
    }

    @Test public void gettingOfferStrategies_withStrategies_returnsThem() {
        stockManager.createAllOffers();

        OfferStrategies strategies = stockManager.getActiveOfferStrategies();

        assertThat(strategies.asSet()).containsExactly(
                stockManager.getAppleOfferStrategy(),
                stockManager.getBeansOfferStrategy(),
                stockManager.getTenPercentOffEverythingOfferStrategy());
    }

    @Test public void gettingOfferStrategies_withoutStrategies_returnsEmptyStrategies() {
        OfferStrategies strategies = stockManager.getActiveOfferStrategies();

        assertThat(strategies.isEmpty()).isTrue();
    }

    @Test public void gettingApplicableOffers_withOffers_returnsThem() {
        stockManager.createAndAddAllItems();
        stockManager.createAllOffers();

        Offers offers = stockManager.getApplicableOffers();

        assertThat(offers.asList()).hasSize(3);
    }

    @Test public void gettingApplicableOffers_withNoOffers_returnsEmptyOffers() {
        stockManager.createAndAddAllItems();

        Offers offers = stockManager.getApplicableOffers();

        assertThat(offers.isEmpty()).isTrue();
    }

    @Test public void gettingOffersValue_withOffers_returnsIt() {
        stockManager.createAndAddAllItems();
        stockManager.createAllOffers();
        Price expectedValue = Price.of(-5320);

        Price value = stockManager.getOffersValue();

        assertThat(value).isEqualTo(expectedValue);
    }

    @Test public void gettingOffersValue_withNoOffers_returnsZero() {
        stockManager.createAndAddAllItems();

        Price value = stockManager.getOffersValue();

        assertThat(value).isEqualTo(Price.ZERO);
    }

    @Test public void gettingStockValueBeforeOffers_withItems_returnsValue() {
        stockManager.createAndAddAllItems();
        Price expectedValue = Price.of(19200);

        Price value = stockManager.getStockValueBeforeOffers();

        assertThat(value).isEqualTo(expectedValue);
    }

    @Test public void gettingStockValueBeforeOffers_withNoItems_returnsZero() {
        Price value = stockManager.getStockValueBeforeOffers();

        assertThat(value).isEqualTo(Price.ZERO);
    }

    @Test public void gettingStockValueAfterOffers_withItemsAndOffers_returnsTotalValue() {
        stockManager.createAndAddAllItems();
        stockManager.createAllOffers();
        Price expectedValue = Price.of(13880);

        Price value = stockManager.getStockValueAfterOffers();

        assertThat(value).isEqualTo(expectedValue);
    }

    @Test public void gettingStockValueAfterOffers_withItemsAndNoOffers_returnsItemsValue() {
        stockManager.createAndAddAllItems();
        Price expectedValue = Price.of(19200);

        Price value = stockManager.getStockValueAfterOffers();

        assertThat(value).isEqualTo(expectedValue);
    }

    @Test public void gettingStockValueAfterOffers_withOffersAndNoItems_returnsZero() {
        stockManager.createAllOffers();

        Price value = stockManager.getStockValueAfterOffers();

        assertThat(value).isEqualTo(Price.ZERO);
    }

    @Test public void gettingStockValueAfterOffers_withNoOffersAndNoItems_returnsZero() {
        Price value = stockManager.getStockValueAfterOffers();

        assertThat(value).isEqualTo(Price.ZERO);
    }
}
