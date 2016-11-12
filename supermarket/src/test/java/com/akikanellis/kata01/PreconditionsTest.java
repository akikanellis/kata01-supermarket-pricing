package com.akikanellis.kata01;

import org.junit.Test;

import static com.akikanellis.kata01.Preconditions.checkNotNegative;
import static com.akikanellis.kata01.Preconditions.checkNotNull;
import static com.akikanellis.kata01.Preconditions.checkNotNullOrEmpty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PreconditionsTest {

    @Test public void checkingReference_withNonNullReference_returnsSameReference() {
        Object expectedReference = new Object();

        Object actualReference = checkNotNull(expectedReference);

        assertThat(actualReference).isSameAs(expectedReference);

    }

    @Test public void checkingReference_withNullReference_throwsException() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> checkNotNull(null));
    }

    @Test public void checkingReference_withNullReferenceAndMessage_throwsExceptionWithMessage() {
        String errorMessage = "Error message";

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> checkNotNull(null, errorMessage))
                .withMessageContaining(errorMessage);
    }

    @Test public void checkingString_withValidString_returnsSameString() {
        String expectedString = "string";

        String actualString = checkNotNullOrEmpty(expectedString);

        assertThat(actualString).isSameAs(expectedString);
    }

    @Test public void checkingString_withNullString_throwsExceptionWithMessageForNullability() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> checkNotNullOrEmpty(null))
                .withMessageContaining("null");
    }

    @Test public void checkingString_withEmptyString_throwsExceptionWithMessageForEmptiness() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> checkNotNullOrEmpty(""))
                .withMessageContaining("empty");
    }

    @Test public void checkingNotNegative_withPositive_returnsNumber() {
        int expectedNumber = 5;

        int actualNumber = checkNotNegative(expectedNumber);

        assertThat(actualNumber).isSameAs(expectedNumber);
    }

    @Test public void checkingNotNegative_withZero_returnsNumber() {
        int expectedNumber = 0;

        int actualNumber = checkNotNegative(expectedNumber);

        assertThat(actualNumber).isSameAs(expectedNumber);
    }

    @Test public void checkingNotNegative_withNegative_throwsExceptionWithMessageForNegative() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> checkNotNegative(-5))
                .withMessageContaining("negative");
    }
}