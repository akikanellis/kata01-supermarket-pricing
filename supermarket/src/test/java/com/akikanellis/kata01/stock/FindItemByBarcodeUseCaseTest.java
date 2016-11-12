package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindItemByBarcodeUseCaseTest {
    @Mock private StockRepository stock;
    private FindItemByBarcodeUseCase findItemByBarcode;

    @Before public void beforeEach() { findItemByBarcode = new FindItemByBarcodeUseCase(stock); }

    @Test public void executing_usesRepository() {
        Item expectedItem = createDefaultItem();
        when(stock.getByBarcode(1)).thenReturn(expectedItem);

        Item actualItem = findItemByBarcode.execute(1);

        assertThat(actualItem).isSameAs(expectedItem);
    }
}