package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategies;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GetActiveOfferStrategiesUseCaseTest {
    @Mock private OfferStrategyRepository offerStrategies;
    private GetActiveOfferStrategiesUseCase getActiveOfferStrategies;

    @Before public void beforeEach() {
        getActiveOfferStrategies = new GetActiveOfferStrategiesUseCase(offerStrategies);
    }

    @Test public void executing_getsOfferStrategiesFromRepository() {
        OfferStrategies expectedOfferStrategies = createDefaultOfferStrategies();
        Mockito.when(offerStrategies.getAll()).thenReturn(expectedOfferStrategies);

        OfferStrategies actualOfferStrategies = getActiveOfferStrategies.execute();

        assertThat(actualOfferStrategies).isSameAs(expectedOfferStrategies);
    }
}