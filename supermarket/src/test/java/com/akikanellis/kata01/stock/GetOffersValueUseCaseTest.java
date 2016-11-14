package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.price.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createOffersWithPrices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetOffersValueUseCaseTest {
    @Mock private GetApplicableOffersUseCase getOffers;
    private GetOffersValueUseCase getOffersValue;

    @Before public void beforeEach() { getOffersValue = new GetOffersValueUseCase(getOffers); }

    @Test public void executing_withApplicableOffers_returnsCumulativeValue() {
        when(getOffers.execute()).thenReturn(createOffersWithPrices(-10, -15, -30));

        Price value = getOffersValue.execute();

        assertThat(value).isEqualTo(Price.of(-55));
    }

    @Test public void executing_withNoApplicableOffers_returnsZero() {
        when(getOffers.execute()).thenReturn(Offers.empty());

        Price value = getOffersValue.execute();

        assertThat(value).isEqualTo(Price.ZERO);
    }
}