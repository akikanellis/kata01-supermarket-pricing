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

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategies;
import static com.akikanellis.kata01.test_utils.Fakes.defaultItems;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetActiveOffersUseCaseTest {
    @Mock private StockRepository stockRepository;
    @Mock private OfferStrategyRepository offerStrategyRepository;
    private GetActiveOffersUseCase getActiveOffers;

    @Before public void beforeEach() {
        getActiveOffers = new GetActiveOffersUseCase(stockRepository, offerStrategyRepository);
    }

    @Test public void gettingOffers_withAvailableOfferStrategies_returnsOffers() {
        OfferStrategies offerStrategies = createDefaultOfferStrategies();
        Items items = defaultItems();
        when(offerStrategyRepository.getAll()).thenReturn(offerStrategies);
        when(stockRepository.getAll()).thenReturn(items);

        Offers offers = getActiveOffers.execute();

        assertThat(offers.asList()).hasSize(3);
    }

    @Test public void gettingOffers_withNoOfferStrategies_returnsEmptyOffers() {
        OfferStrategies offerStrategies = OfferStrategies.empty();
        Items items = defaultItems();
        when(offerStrategyRepository.getAll()).thenReturn(offerStrategies);
        when(stockRepository.getAll()).thenReturn(items);

        Offers offers = getActiveOffers.execute();

        assertThat(offers.asList()).isEmpty();

    }

    @Test public void gettingOffers_withNoGroceries_returnsEmptyOffers() {
        OfferStrategies offerStrategies = createDefaultOfferStrategies();
        Items items = Items.empty();
        when(offerStrategyRepository.getAll()).thenReturn(offerStrategies);
        when(stockRepository.getAll()).thenReturn(items);

        Offers offers = getActiveOffers.execute();

        assertThat(offers.asList()).isEmpty();

    }
}