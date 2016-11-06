package com.akikanellis.kata01;

import org.junit.Before;
import org.junit.Test;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategy;
import static org.assertj.core.api.Assertions.assertThat;

public class OfferStrategyRepositoryTest {
    private OfferStrategyRepository offerStrategies;

    @Before public void beforeEach() { offerStrategies = new OfferStrategyRepository(); }

    @Test public void addingStrategy_addsToStrategies() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();

        offerStrategies.add(offerStrategy);

        assertThat(offerStrategies.getAll().asList()).containsOnly(offerStrategy);
    }

    @Test public void removingStrategy_removesFromStrategies() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();
        offerStrategies.add(offerStrategy);

        offerStrategies.remove(offerStrategy);

        assertThat(offerStrategies.getAll().asList()).isEmpty();
    }
}