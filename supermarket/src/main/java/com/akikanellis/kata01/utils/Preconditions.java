package com.akikanellis.kata01.utils;

/**
 * Utilities regarding preconditions in arguments, parameters, fields etc.
 */
public final class Preconditions {

    private Preconditions() { throw new AssertionError("No instances."); }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) throw new NullPointerException();

        return reference;
    }

    public static <T> T checkNotNull(T reference, String errorMessage) {
        if (reference == null) throw new NullPointerException(errorMessage);

        return reference;
    }

    public static String checkNotNullOrEmpty(String s) {
        checkNotNull(s, "String is null.");
        if (s.isEmpty()) throw new IllegalArgumentException("String is empty.");

        return s;
    }

    public static long checkNotNegative(long n) {
        if (n < 0) throw new IllegalArgumentException(String.format("Argument can't be negative. Found [%d]", n));

        return n;
    }

}
