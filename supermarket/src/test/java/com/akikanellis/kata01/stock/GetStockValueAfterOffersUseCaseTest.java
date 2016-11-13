package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.price.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetStockValueAfterOffersUseCaseTest {
    @Mock private GetOffersValueUseCase getOffersValue;
    @Mock private GetStockValueBeforeOffersUseCase getStockValueBeforeOffers;
    private GetStockValueAfterOffersUseCase getStockValueAfterOffers;

    @Before public void beforeEach() {
        getStockValueAfterOffers = new GetStockValueAfterOffersUseCase(getStockValueBeforeOffers, getOffersValue);
    }

    @Test public void executing_withNonZeroStockAndOffersValue_returnsStockValuePlusOffersValue() {
        when(getOffersValue.execute()).thenReturn(Price.of(-400));
        when(getStockValueBeforeOffers.execute()).thenReturn(Price.of(2500));

        Price price = getStockValueAfterOffers.execute();

        assertThat(price).isEqualTo(Price.of(2100));
    }

    @Test public void executing_withZeroStockValueButNonZeroOffersValue_returnsZero() {
        when(getOffersValue.execute()).thenReturn(Price.of(-400));
        when(getStockValueBeforeOffers.execute()).thenReturn(Price.ZERO);

        Price price = getStockValueAfterOffers.execute();

        assertThat(price).isEqualTo(Price.ZERO);
    }

    @Test public void executing_withNonZeroStockValueButZeroOffersValue_returnsValueOfStock() {
        when(getOffersValue.execute()).thenReturn(Price.ZERO);
        when(getStockValueBeforeOffers.execute()).thenReturn(Price.of(2500));

        Price price = getStockValueAfterOffers.execute();

        assertThat(price).isEqualTo(Price.of(2500));
    }
}