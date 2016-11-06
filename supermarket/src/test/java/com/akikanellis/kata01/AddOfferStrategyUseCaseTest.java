package com.akikanellis.kata01;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddOfferStrategyUseCaseTest {
    @Mock private OfferStrategyRepository offerStrategies;
    private AddOfferStrategyUseCase addOfferStrategyUseCase;

    @Before public void beforeEach() { addOfferStrategyUseCase = new AddOfferStrategyUseCase(offerStrategies); }

    @Test public void addingOfferStrategy_addsStrategyInRepository() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();

        addOfferStrategyUseCase.execute(offerStrategy);

        verify(offerStrategies).add(offerStrategy);
    }
}