package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItem;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReduceStockUseCaseTest {
    @Mock private StockRepository stock;
    @Mock private AddNewItemIfNotExistsUseCase addNewItemIfNotExists;
    private ReduceStockUseCase reduceStock;

    @Before public void beforeEach() { reduceStock = new ReduceStockUseCase(stock, addNewItemIfNotExists); }

    @Test public void executing_addsItemToStockFirst() {
        Item item = createDefaultItem();

        reduceStock.execute(item, 50);

        verify(addNewItemIfNotExists).execute(item);
    }

    @Test public void executing_withCurrentQuantityBigger_replacesStockWithTheDifference() {
        Item item = createDefaultItem();
        when(stock.getQuantity(item)).thenReturn(50);

        reduceStock.execute(item, 20);

        verify(stock).replaceQuantity(item, 30);
    }

    @Test public void executing_withCurrentQuantitySmaller_replacesStockWithZero() {
        Item item = createDefaultItem();
        when(stock.getQuantity(item)).thenReturn(10);

        reduceStock.execute(item, 15);

        verify(stock).replaceQuantity(item, 0);
    }

    @Test public void executing_withNegativeQuantity_throwsException() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reduceStock.execute(createDefaultItem(), -5));
    }
}