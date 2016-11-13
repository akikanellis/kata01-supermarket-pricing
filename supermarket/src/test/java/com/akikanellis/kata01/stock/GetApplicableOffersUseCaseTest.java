package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategyRepository;
import com.akikanellis.kata01.offer.Offers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItems;
import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategies;
import static com.akikanellis.kata01.test_utils.Fakes.createOfferStrategiesWithQuantities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetApplicableOffersUseCaseTest {
    @Mock private StockRepository stock;
    @Mock private OfferStrategyRepository offerStrategies;
    private GetApplicableOffersUseCase getApplicableOffers;

    @Before public void beforeEach() {
        getApplicableOffers = new GetApplicableOffersUseCase(stock, offerStrategies);
    }

    @Test public void gettingOffers_withAvailableOfferStrategies_returnsOffers() {
        OfferStrategies strategies = createOfferStrategiesWithQuantities(1, 2, 3);
        Items items = createDefaultItems();
        when(offerStrategies.getAll()).thenReturn(strategies);
        when(stock.getAll()).thenReturn(items);

        Offers offers = getApplicableOffers.execute();

        assertThat(offers.asList()).hasSize(3);
    }

    @Test public void gettingOffers_withNoOfferStrategies_returnsEmptyOffers() {
        OfferStrategies strategies = OfferStrategies.empty();
        Items items = createDefaultItems();
        when(offerStrategies.getAll()).thenReturn(strategies);
        when(stock.getAll()).thenReturn(items);

        Offers offers = getApplicableOffers.execute();

        assertThat(offers.isEmpty()).isTrue();

    }

    @Test public void gettingOffers_withNoGroceries_returnsEmptyOffers() {
        OfferStrategies strategies = createDefaultOfferStrategies();
        Items items = Items.empty();
        when(offerStrategies.getAll()).thenReturn(strategies);
        when(stock.getAll()).thenReturn(items);

        Offers offers = getApplicableOffers.execute();

        assertThat(offers.isEmpty()).isTrue();

    }
}