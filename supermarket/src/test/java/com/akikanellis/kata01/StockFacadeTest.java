package com.akikanellis.kata01;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StockFacadeTest {
    @Mock private AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase;
    @Mock private FillStockUseCase fillStockUseCase;
    private StockFacade stockFacade;

    @Before public void beforeEach() { stockFacade = new StockFacade(addNewItemIfNotExistsUseCase, fillStockUseCase); }

    @Test public void addingNewItem_usesUseCase() {
        Item item = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ONE)
                .build();

        stockFacade.addNewItem(item);

        verify(addNewItemIfNotExistsUseCase).execute(item);
    }

    @Test public void fillingStock_usesStockUseCase() {
        Item item = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ONE)
                .build();

        stockFacade.fillStock(item, 50);

        verify(fillStockUseCase).execute(item, 50);
    }
}