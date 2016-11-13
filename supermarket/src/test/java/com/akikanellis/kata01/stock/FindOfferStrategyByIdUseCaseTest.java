package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.OfferStrategyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindOfferStrategyByIdUseCaseTest {
    @Mock private OfferStrategyRepository offerStrategies;
    private FindOfferStrategyByIdUseCase findOfferStrategyById;

    @Before public void beforeEach() { findOfferStrategyById = new FindOfferStrategyByIdUseCase(offerStrategies); }

    @Test public void executing_usesRepository() {
        OfferStrategy expectedOfferStrategy = createDefaultOfferStrategy();
        when(offerStrategies.getById(1)).thenReturn(expectedOfferStrategy);

        OfferStrategy actualOfferStrategy = findOfferStrategyById.execute(1);

        assertThat(actualOfferStrategy).isSameAs(expectedOfferStrategy);
    }
}