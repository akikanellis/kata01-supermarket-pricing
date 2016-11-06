package com.akikanellis.kata01;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RemoveOfferStrategyUseCaseTest {
    @Mock private OfferStrategyRepository offerStrategies;
    private RemoveOfferStrategyUseCase removeOfferStrategyUseCase;

    @Before public void beforeEach() { removeOfferStrategyUseCase = new RemoveOfferStrategyUseCase(offerStrategies); }

    @Test public void addingOfferStrategy_addsStrategyInRepository() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();

        removeOfferStrategyUseCase.execute(offerStrategy);

        verify(offerStrategies).remove(offerStrategy);
    }
}