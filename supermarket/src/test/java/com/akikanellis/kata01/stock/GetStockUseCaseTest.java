package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Items;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItems;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetStockUseCaseTest {
    @Mock private StockRepository stock;
    private GetStockUseCase getStock;

    @Before public void beforeEach() { getStock = new GetStockUseCase(stock); }

    @Test public void executing_returnsStockFromRepository() {
        Items expectedItems = createDefaultItems();
        when(stock.getAll()).thenReturn(expectedItems);

        Items actualItems = getStock.execute();

        assertThat(actualItems).isSameAs(expectedItems);
    }
}