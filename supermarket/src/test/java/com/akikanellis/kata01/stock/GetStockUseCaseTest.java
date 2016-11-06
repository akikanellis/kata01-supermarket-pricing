package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.Items;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.defaultItems;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetStockUseCaseTest {
    @Mock private StockRepository stock;
    private GetStockUseCase getStock;

    @Before public void beforeEach() { getStock = new GetStockUseCase(stock); }

    @Test public void gettingStock_returnsStockFromRepository() {
        Items expectedItems = defaultItems();
        when(stock.getAll()).thenReturn(expectedItems);

        Items actualItems = getStock.execute();

        assertThat(actualItems).isSameAs(expectedItems);
    }
}