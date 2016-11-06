package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.test_utils.Fakes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GetActiveOfferStrategiesUseCaseTest {
    @Mock private OfferStrategyRepository offerStrategies;
    private GetActiveOfferStrategiesUseCase getActiveOfferStrategiesUseCase;

    @Before public void beforeEach() {
        getActiveOfferStrategiesUseCase = new GetActiveOfferStrategiesUseCase(offerStrategies);
    }

    @Test public void executing_getsOfferStrategiesFromRepository() {
        OfferStrategies expectedOfferStrategies = Fakes.createDefaultOfferStrategies();
        Mockito.when(offerStrategies.getAll()).thenReturn(expectedOfferStrategies);

        OfferStrategies actualOfferStrategies = getActiveOfferStrategiesUseCase.execute();

        assertThat(actualOfferStrategies).isSameAs(expectedOfferStrategies);
    }
}