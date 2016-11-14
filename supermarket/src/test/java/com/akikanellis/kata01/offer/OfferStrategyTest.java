package com.akikanellis.kata01.offer;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class OfferStrategyTest {

    @Test public void equals_isCorrect() {
        EqualsVerifier.forClass(OfferStrategy.class)
                .withIgnoredFields("description")
                .verify();
    }
}