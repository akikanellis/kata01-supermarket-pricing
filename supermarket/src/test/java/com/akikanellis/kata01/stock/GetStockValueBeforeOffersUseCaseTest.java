package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.price.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createItemsWithPrices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetStockValueBeforeOffersUseCaseTest {
    @Mock private GetStockUseCase getStock;
    private GetStockValueBeforeOffersUseCase getStockValueBeforeOffers;

    @Before public void beforeEach() { getStockValueBeforeOffers = new GetStockValueBeforeOffersUseCase(getStock); }

    @Test public void gettingValue_withOneItem_returnsItemValue() {
        when(getStock.execute()).thenReturn(createItemsWithPrices(50));

        Price value = getStockValueBeforeOffers.execute();

        assertThat(value).isEqualTo(Price.of(50));
    }

    @Test public void gettingValue_withManyItems_returnsCumulativeValue() {
        when(getStock.execute()).thenReturn(createItemsWithPrices(50, 90, 30));

        Price value = getStockValueBeforeOffers.execute();

        assertThat(value).isEqualTo(Price.of(170));
    }

    @Test public void gettingValue_withNoItems_returnsZero() {
        when(getStock.execute()).thenReturn(Items.empty());

        Price value = getStockValueBeforeOffers.execute();

        assertThat(value).isEqualTo(Price.ZERO);
    }
}