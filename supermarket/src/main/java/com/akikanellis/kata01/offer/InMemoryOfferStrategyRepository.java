package com.akikanellis.kata01.offer;

import java.util.HashSet;
import java.util.Set;

public class InMemoryOfferStrategyRepository implements OfferStrategyRepository {
    private final Set<OfferStrategy> offerStrategies;

    public InMemoryOfferStrategyRepository() { this.offerStrategies = new HashSet<>(); }

    @Override public void add(OfferStrategy offerStrategy) { offerStrategies.add(offerStrategy); }

    @Override public void remove(OfferStrategy offerStrategy) { offerStrategies.remove(offerStrategy); }

    @Override public OfferStrategies getAll() { return OfferStrategies.fromCollection(offerStrategies); }
}
