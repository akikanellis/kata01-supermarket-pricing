package com.akikanellis.kata01.offer;

import java.util.HashSet;
import java.util.Set;

public class InMemoryOfferStrategyRepository implements OfferStrategyRepository {
    private final Set<OfferStrategy> offerStrategies;

    public InMemoryOfferStrategyRepository() { this.offerStrategies = new HashSet<>(); }

    @Override public boolean contains(OfferStrategy offerStrategy) { return offerStrategies.contains(offerStrategy); }

    @Override public void add(OfferStrategy offerStrategy) { offerStrategies.add(offerStrategy); }

    @Override public void remove(OfferStrategy offerStrategy) { offerStrategies.remove(offerStrategy); }

    @Override public OfferStrategy getById(long id) {
        return offerStrategies.stream()
                .filter(offerStrategy -> offerStrategy.id() == id)
                .findFirst()
                .orElseThrow(() -> new OfferStrategyDoesNotExistException(id));
    }

    @Override public OfferStrategies getAll() { return OfferStrategies.fromCollection(offerStrategies); }
}
