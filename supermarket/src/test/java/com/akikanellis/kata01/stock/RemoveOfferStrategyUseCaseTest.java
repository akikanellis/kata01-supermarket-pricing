package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.OfferStrategyRepository;
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
    private RemoveOfferStrategyUseCase removeOfferStrategy;

    @Before public void beforeEach() { removeOfferStrategy = new RemoveOfferStrategyUseCase(offerStrategies); }

    @Test public void executing_removesStrategyFromRepository() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();

        removeOfferStrategy.execute(offerStrategy);

        verify(offerStrategies).remove(offerStrategy);
    }
}