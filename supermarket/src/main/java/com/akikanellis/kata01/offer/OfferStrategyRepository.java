package com.akikanellis.kata01.offer;

import java.util.HashSet;
import java.util.Set;

public class OfferStrategyRepository {
    private final Set<OfferStrategy> offerStrategies;

    public OfferStrategyRepository() { this.offerStrategies = new HashSet<>(); }

    public void add(OfferStrategy offerStrategy) { offerStrategies.add(offerStrategy); }

    public void remove(OfferStrategy offerStrategy) { offerStrategies.remove(offerStrategy); }

    public OfferStrategies getAll() { return OfferStrategies.fromCollection(offerStrategies); }
}
