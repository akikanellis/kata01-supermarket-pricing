package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
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
    private StockFacade stockFacade;

    @Before public void beforeEach() {
        stockFacade = new StockFacade(addNewItemIfNotExistsUseCase, fillStockUseCase, reduceStock, getStock,
                addOfferStrategy, removeOfferStrategy, getActiveOfferStrategies);
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
}