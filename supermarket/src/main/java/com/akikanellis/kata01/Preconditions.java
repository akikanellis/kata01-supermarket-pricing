package com.akikanellis.kata01;

/**
 * Utilities regarding preconditions in arguments, parameters, fields etc.
 */
final class Preconditions {

    private Preconditions() { throw new AssertionError("No instances."); }

    static <T> T checkNotNull(T reference) {
        if (reference == null) throw new NullPointerException();

        return reference;
    }

    static <T> T checkNotNull(T reference, String errorMessage) {
        if (reference == null) throw new NullPointerException(errorMessage);

        return reference;
    }

    static String checkNotNullOrEmpty(String s) {
        checkNotNull(s, "String is null.");
        if (s.isEmpty()) throw new IllegalArgumentException("String is empty.");

        return s;
    }
}
