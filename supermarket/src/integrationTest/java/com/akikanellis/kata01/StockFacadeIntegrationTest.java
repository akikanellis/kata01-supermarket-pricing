package com.akikanellis.kata01;

import com.akikanellis.kata01.item.ItemDoesNotExistException;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.item.QuantifiedItem;
import com.akikanellis.kata01.offer.InMemoryOfferStrategyRepository;
import com.akikanellis.kata01.offer.OfferStrategyRepository;
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

        StockFacade stockFacade = new StockFacade(addNewItemIfNotExists, findItemByBarcode, fillStock, reduceStock, getStock,
                findOfferStrategyById, addOfferStrategy, removeOfferStrategy, getActiveOfferStrategies,
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
        stockManager.createAppleBeansAndCheeseOffers();

        assertThat(stockManager.getStock().asItemsList())
                .containsOnly(stockManager.getApple(), stockManager.getCheese(), stockManager.getBeans());
    }
}
