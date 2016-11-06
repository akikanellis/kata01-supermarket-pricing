package com.akikanellis.kata01;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItem;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddNewItemUseCaseTest {
    @Mock private StockRepository stock;
    private AddNewItemUseCase addNewItemUseCase;

    @Before public void beforeEach() { addNewItemUseCase = new AddNewItemUseCase(stock); }

    @Test public void executing_withItemNotYetCreated_createsNewItemInRepository() {
        Item item = createDefaultItem();
        when(stock.contains(item)).thenReturn(false);

        addNewItemUseCase.execute(item);

        verify(stock).create(item);
    }

    @Test public void executing_withItemAlreadyCreated_doesNothing() {
        Item item = createDefaultItem();
        when(stock.contains(item)).thenReturn(true);

        addNewItemUseCase.execute(item);

        verify(stock, never()).create(any(Item.class));
    }
}