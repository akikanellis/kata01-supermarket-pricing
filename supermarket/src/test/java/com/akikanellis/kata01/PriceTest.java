package com.akikanellis.kata01;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PriceTest {

    @Test public void creating_withNullAmount_throwsException() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Price(null));
    }

    @Test public void equals_isCorrect() { EqualsVerifier.forClass(Price.class).verify(); }
}