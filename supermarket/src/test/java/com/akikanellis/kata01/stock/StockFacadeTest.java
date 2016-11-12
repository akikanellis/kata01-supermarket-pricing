package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.price.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItem;
import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategies;
import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategy;
import static com.akikanellis.kata01.test_utils.Fakes.defaultItems;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockFacadeTest {
    @Mock private AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase;
    @Mock private FillStockUseCase fillStockUseCase;
    @Mock private ReduceStockUseCase reduceStock;
    @Mock private GetStockUseCase getStock;
    @Mock private AddOfferStrategyUseCase addOfferStrategy;
    @Mock private RemoveOfferStrategyUseCase removeOfferStrategy;
    @Mock private GetActiveOfferStrategiesUseCase getActiveOfferStrategies;
    @Mock private GetStockValueBeforeOffersUseCase getStockValueBeforeOffers;
    @Mock private GetOffersValueUseCase getOffersValue;
    @Mock private GetStockValueAfterOffers getStockValueAfterOffers;
    private StockFacade stockFacade;

    @Before public void beforeEach() {
        stockFacade = new StockFacade(addNewItemIfNotExistsUseCase, fillStockUseCase, reduceStock, getStock,
                addOfferStrategy, removeOfferStrategy, getActiveOfferStrategies, getOffersValue, getStockValueBeforeOffers,
                getStockValueAfterOffers);
    }

    @Test public void addingNewItem_usesUseCase() {
        Item item = createDefaultItem();

        stockFacade.addNewItem(item);

        verify(addNewItemIfNotExistsUseCase).execute(item);
    }

    @Test public void fillingStock_usesFillStockUseCase() {
        Item item = createDefaultItem();

        stockFacade.fillStock(item, 50);

        verify(fillStockUseCase).execute(item, 50);
    }

    @Test public void reducingStock_usesReduceStockUseCase() {
        Item item = createDefaultItem();

        stockFacade.reduceStock(item, 50);

        verify(reduceStock).execute(item, 50);
    }

    @Test public void gettingStock_usesGetStockUseCase() {
        Items expectedItems = defaultItems();
        when(getStock.execute()).thenReturn(expectedItems);

        Items actualItems = stockFacade.getStock();

        assertThat(actualItems).isSameAs(expectedItems);
    }

    @Test public void addingOfferStrategy_usesAddOfferStrategyUseCase() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();

        stockFacade.addOfferStrategy(offerStrategy);

        verify(addOfferStrategy).execute(offerStrategy);
    }

    @Test public void removingOfferStrategy_usesRemoveOfferStrategyUseCase() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();

        stockFacade.removeOfferStrategy(offerStrategy);

        verify(removeOfferStrategy).execute(offerStrategy);
    }

    @Test public void gettingActiveOfferStrategies_usesUseCase() {
        OfferStrategies expectedOfferStrategies = createDefaultOfferStrategies();
        when(getActiveOfferStrategies.execute()).thenReturn(expectedOfferStrategies);

        OfferStrategies actualOfferStrategies = stockFacade.getActiveOfferStrategies();

        assertThat(actualOfferStrategies).isSameAs(expectedOfferStrategies);
    }

    @Test public void gettingOffersValue_usesUseCase() {
        Price value = Price.from(-15);
        when(getOffersValue.execute()).thenReturn(value);

        Price offersValue = stockFacade.getOffersValue();

        assertThat(offersValue).isSameAs(value);
    }

    @Test public void gettingStockValueBeforeOffers_usesUseCase() {
        Price value = Price.from(50);
        when(getStockValueBeforeOffers.execute()).thenReturn(value);

        Price stockValueBeforeOffers = stockFacade.getStockValueBeforeOffers();

        assertThat(stockValueBeforeOffers).isSameAs(value);
    }

    @Test public void gettingStockValueAfterOffers_usesUseCase() {
        Price value = Price.from(35);
        when(getStockValueAfterOffers.execute()).thenReturn(value);

        Price stockValueAfterOffers = stockFacade.getStockValueAfterOffers();

        assertThat(stockValueAfterOffers).isSameAs(value);
    }
}