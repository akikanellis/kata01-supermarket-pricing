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
public class FillStockUseCaseTest {
    @Mock private StockRepository stock;
    @Mock private AddNewItemIfNotExistsUseCase addNewItemIfNotExists;
    private FillStockUseCase fillStock;

    @Before public void beforeEach() { fillStock = new FillStockUseCase(stock, addNewItemIfNotExists); }

    @Test public void executing_addsItemToStockFirst() {
        Item item = createDefaultItem();

        fillStock.execute(item, 50);

        verify(addNewItemIfNotExists).execute(item);
    }

    @Test public void executing_withNonNegativeQuantity_addsStockOnTopOfPreviousQuantity() {
        Item item = createDefaultItem();
        when(stock.getQuantity(item)).thenReturn(20);

        fillStock.execute(item, 50);

        verify(stock).replaceQuantity(item, 70);
    }

    @Test public void executing_withNegativeQuantity_throwsException() {
        Item item = createDefaultItem();

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> fillStock.execute(item, -5));
    }
}