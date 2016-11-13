package com.akikanellis.kata01.offer;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * A representation of a collection of {@link com.akikanellis.kata01.offer.OfferStrategies}.
 */
public final class OfferStrategies {
    private final Set<OfferStrategy> strategies;

    private OfferStrategies(Collection<OfferStrategy> strategies) { this.strategies = new HashSet<>(strategies); }

    public static OfferStrategies fromCollection(Collection<OfferStrategy> offerStrategies) {
        return new OfferStrategies(offerStrategies);
    }

    public static OfferStrategies empty() { return new OfferStrategies(Collections.emptyList()); }

    public boolean isEmpty() { return strategies.isEmpty(); }

    public Set<OfferStrategy> asSet() { return new HashSet<>(strategies); }

    public Stream<OfferStrategy> stream() { return strategies.stream(); }
}
