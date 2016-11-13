package com.akikanellis.kata01.offer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Offers {
    private final List<QuantifiedOffer> quantifiedOffers;

    private Offers(Collection<QuantifiedOffer> quantifiedOffers) {
        this.quantifiedOffers = new ArrayList<>(quantifiedOffers);
    }

    public static Offers fromCollection(Collection<QuantifiedOffer> quantifiedOffers) {
        return new Offers(quantifiedOffers);
    }

    public static Offers empty() { return fromCollection(Collections.emptyList()); }

    public static Offers fromSingle(QuantifiedOffer quantifiedOffer) {
        return fromCollection(Collections.singletonList(quantifiedOffer));
    }

    public boolean isEmpty() { return quantifiedOffers.isEmpty(); }

    public List<QuantifiedOffer> asList() { return new ArrayList<>(quantifiedOffers); }

    public Stream<QuantifiedOffer> stream() { return quantifiedOffers.stream(); }

    public Offers join(Offers other) {
        List<QuantifiedOffer> joined = Stream.concat(stream(), other.stream())
                .collect(Collectors.toList());

        return fromCollection(joined);
    }
}
