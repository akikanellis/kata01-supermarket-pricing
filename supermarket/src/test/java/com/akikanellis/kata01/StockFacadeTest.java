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
    private StockFacade stockFacade;

    @Before public void beforeEach() { stockFacade = new StockFacade(addNewItemIfNotExistsUseCase); }

    @Test public void addingNewItem_usesUseCase() {
        Item item = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ONE)
                .build();

        stockFacade.addNewItem(item);

        verify(addNewItemIfNotExistsUseCase).execute(item);
    }
}