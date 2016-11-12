package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
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
public class AddNewItemIfNotExistsUseCaseTest {
    @Mock private StockRepository stock;
    private AddNewItemIfNotExistsUseCase addNewItemIfNotExistsUseCase;

    @Before public void beforeEach() { addNewItemIfNotExistsUseCase = new AddNewItemIfNotExistsUseCase(stock); }

    @Test public void executing_withItemNotYetCreated_createsNewItemInRepository() {
        Item item = createDefaultItem();
        when(stock.contains(item)).thenReturn(false);

        addNewItemIfNotExistsUseCase.execute(item);

        verify(stock).create(item);
    }

    @Test public void executing_withItemAlreadyCreated_doesNothing() {
        Item item = createDefaultItem();
        when(stock.contains(item)).thenReturn(true);

        addNewItemIfNotExistsUseCase.execute(item);

        verify(stock, never()).create(any(Item.class));
    }
}