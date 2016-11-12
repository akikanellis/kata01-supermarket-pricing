package com.akikanellis.kata01.offer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public final class OfferStrategies {
    private final Set<OfferStrategy> strategies;

    private OfferStrategies(Collection<OfferStrategy> strategies) { this.strategies = new HashSet<>(strategies); }

    public static OfferStrategies fromCollection(Collection<OfferStrategy> offerStrategies) {
        return new OfferStrategies(offerStrategies);
    }

    public static OfferStrategies empty() { return new OfferStrategies(Collections.emptyList()); }

    public List<OfferStrategy> asList() { return new ArrayList<>(strategies); }

    public Stream<OfferStrategy> stream() { return asList().stream(); }
}
