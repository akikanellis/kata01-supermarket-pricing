package com.akikanellis.kata01.offer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class OfferStrategies {
    private final Set<OfferStrategy> strategies;

    private OfferStrategies(Collection<OfferStrategy> strategies) { this.strategies = new HashSet<>(strategies); }

    public static OfferStrategies fromCollection(Collection<OfferStrategy> offerStrategies) {
        return new OfferStrategies(offerStrategies);
    }

    public List<OfferStrategy> asList() { return new ArrayList<>(strategies); }
}
