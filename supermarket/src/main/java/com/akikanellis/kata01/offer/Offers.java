package com.akikanellis.kata01.offer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Offers {
    private final List<OfferWithQuantity> offersWithQuantities;

    private Offers(Collection<OfferWithQuantity> offersWithQuantities) {
        this.offersWithQuantities = new ArrayList<>(offersWithQuantities);
    }

    public static Offers fromCollection(Collection<OfferWithQuantity> offersWithQuantities) {
        return new Offers(offersWithQuantities);
    }

    public static Offers empty() { return fromCollection(Collections.emptyList()); }

    public boolean isEmpty() { return offersWithQuantities.isEmpty(); }

    public List<OfferWithQuantity> asList() { return new ArrayList<>(offersWithQuantities); }

    public Stream<OfferWithQuantity> stream() { return offersWithQuantities.stream(); }

    public Offers join(Offers other) {
        List<OfferWithQuantity> joined = Stream.concat(stream(), other.stream())
                .collect(Collectors.toList());

        return fromCollection(joined);
    }
}
