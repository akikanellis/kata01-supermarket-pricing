package com.akikanellis.kata01.offer;

import org.junit.Before;
import org.junit.Test;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultOfferStrategy;
import static com.akikanellis.kata01.test_utils.Fakes.createOfferStrategyWithId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class InMemoryOfferStrategyRepositoryTest {
    private InMemoryOfferStrategyRepository offerStrategies;

    @Before public void beforeEach() { offerStrategies = new InMemoryOfferStrategyRepository(); }

    @Test public void addingStrategy_addsToStrategies() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();

        offerStrategies.add(offerStrategy);

        assertThat(offerStrategies.getAll().asSet()).containsOnly(offerStrategy);
    }

    @Test public void removingStrategy_removesFromStrategies() {
        OfferStrategy offerStrategy = createDefaultOfferStrategy();
        offerStrategies.add(offerStrategy);

        offerStrategies.remove(offerStrategy);

        assertThat(offerStrategies.getAll().isEmpty()).isTrue();
    }

    @Test public void gettingById_withExistingStrategy_returnsStrategy() {
        long id = 11;
        OfferStrategy expectedOfferStrategy = createOfferStrategyWithId(id);
        offerStrategies.add(expectedOfferStrategy);

        OfferStrategy actualOfferStrategy = offerStrategies.getById(id);

        assertThat(actualOfferStrategy).isEqualTo(expectedOfferStrategy);
    }

    @Test public void gettingById_withNoExistingStrategy_throwsException() {
        assertThatExceptionOfType(OfferStrategyDoesNotExistException.class)
                .isThrownBy(() -> offerStrategies.getById(11));
    }
}