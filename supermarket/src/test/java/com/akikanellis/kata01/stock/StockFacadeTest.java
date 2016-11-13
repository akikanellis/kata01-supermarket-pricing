package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.price.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItem;
import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategies;
import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategy;
import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOffers;
import static com.akikanellis.kata01.test_utils.Fakes.defaultItems;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockFacadeTest {
    @Mock private AddNewItemIfNotExistsUseCase addNewItemIfNotExists;
    @Mock private FindItemByBarcodeUseCase findItemByBarcode;
    @Mock private FillStockUseCase fillStock;
    @Mock private ReduceStockUseCase reduceStock;
    @Mock private GetStockUseCase getStock;
    @Mock private FindOfferStrategyByIdUseCase findOfferStrategyById;
    @Mock private AddOfferStrategyUseCase addOfferStrategy;
    @Mock private RemoveOfferStrategyUseCase removeOfferStrategy;
    @Mock private GetActiveOfferStrategiesUseCase getActiveOfferStrategies;
    @Mock private GetStockValueBeforeOffersUseCase getStockValueBeforeOffers;
    @Mock private GetOffersValueUseCase getOffersValue;
    @Mock private GetStockValueAfterOffersUseCase getStockValueAfterOffers;
    @Mock private GetApplicableOffersUseCase getApplicableOffers;
    private StockFacade stockFacade;

    @Before public void beforeEach() {
        stockFacade = new StockFacade(addNewItemIfNotExists, findItemByBarcode, fillStock, reduceStock, getStock,
                findOfferStrategyById, addOfferStrategy, removeOfferStrategy, getActiveOfferStrategies,
                getApplicableOffers, getOffersValue, getStockValueBeforeOffers, getStockValueAfterOffers);
    }

    @Test public void addingNewItem_usesUseCase() {
        Item item = createDefaultItem();

        stockFacade.addNewItem(item);

        verify(addNewItemIfNotExists).execute(item);
    }

    @Test public void fillingStock_usesFoundItemToFillStock() {
        Item item = createDefaultItem();
        when(findItemByBarcode.execute(10)).thenReturn(item);

        stockFacade.fillStock(10, 50);

        verify(fillStock).execute(item, 50);
    }

    @Test public void reducingStock_usesFoundItemToReduceStock() {
        Item item = createDefaultItem();
        when(findItemByBarcode.execute(10)).thenReturn(item);

        stockFacade.reduceStock(10, 50);

        verify(reduceStock).execute(item, 50);
    }

    @Test public void gettingStock_usesUseCase() {
        Items expectedItems = defaultItems();
        when(getStock.execute()).thenReturn(expectedItems);

        Items actualItems = stockFacade.getStock();

        assertThat(actualItems).isSameAs(expectedItems);
    }

    @Test public void addingOfferStrategy_usesUseCase() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();

        stockFacade.addOfferStrategy(offerStrategy);

        verify(addOfferStrategy).execute(offerStrategy);
    }

    @Test public void removingOfferStrategy_usesFoundStrategyToRemoveIt() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();
        when(findOfferStrategyById.execute(11)).thenReturn(offerStrategy);

        stockFacade.removeOfferStrategy(11);

        verify(removeOfferStrategy).execute(offerStrategy);
    }

    @Test public void gettingActiveOfferStrategies_usesUseCase() {
        OfferStrategies expectedOfferStrategies = createDefaultOfferStrategies();
        when(getActiveOfferStrategies.execute()).thenReturn(expectedOfferStrategies);

        OfferStrategies actualOfferStrategies = stockFacade.getActiveOfferStrategies();

        assertThat(actualOfferStrategies).isSameAs(expectedOfferStrategies);
    }

    @Test public void gettingApplicableOffers_usesUseCase() {
        Offers expectedOffers = createDefaultOffers();
        when(getApplicableOffers.execute()).thenReturn(expectedOffers);

        Offers actualOffers = stockFacade.getApplicableOffers();

        assertThat(actualOffers).isSameAs(expectedOffers);
    }

    @Test public void gettingOffersValue_usesUseCase() {
        Price value = Price.of(-15);
        when(getOffersValue.execute()).thenReturn(value);

        Price offersValue = stockFacade.getOffersValue();

        assertThat(offersValue).isSameAs(value);
    }

    @Test public void gettingStockValueBeforeOffers_usesUseCase() {
        Price value = Price.of(50);
        when(getStockValueBeforeOffers.execute()).thenReturn(value);

        Price stockValueBeforeOffers = stockFacade.getStockValueBeforeOffers();

        assertThat(stockValueBeforeOffers).isSameAs(value);
    }

    @Test public void gettingStockValueAfterOffers_usesUseCase() {
        Price value = Price.of(35);
        when(getStockValueAfterOffers.execute()).thenReturn(value);

        Price stockValueAfterOffers = stockFacade.getStockValueAfterOffers();

        assertThat(stockValueAfterOffers).isSameAs(value);
    }
}