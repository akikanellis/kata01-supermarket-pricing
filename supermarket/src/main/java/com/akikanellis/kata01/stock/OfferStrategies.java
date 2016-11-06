package com.akikanellis.kata01.stock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OfferStrategies {
    private final Set<OfferStrategy> offerStrategies;

    private OfferStrategies(Collection<OfferStrategy> offerStrategies) {
        this.offerStrategies = new HashSet<>(offerStrategies);
    }

    public static OfferStrategies fromCollection(Collection<OfferStrategy> offerStrategies) {
        return new OfferStrategies(offerStrategies);
    }

    public List<OfferStrategy> asList() { return new ArrayList<>(offerStrategies); }
}
