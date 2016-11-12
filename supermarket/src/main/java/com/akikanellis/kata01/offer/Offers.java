package com.akikanellis.kata01.offer;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Offers {
    private final List<OfferWithQuantity> offersWithQuantities;

    private Offers(List<OfferWithQuantity> offersWithQuantities) { this.offersWithQuantities = offersWithQuantities; }

    public static Offers fromList(List<OfferWithQuantity> itemsWithQuantities) {
        return new Offers(itemsWithQuantities);
    }

    public static Offers fromEntries(Set<Map.Entry<Offer, Integer>> entries) {
        List<OfferWithQuantity> itemsWithQuantities = entries.stream()
                .map(entry -> OfferWithQuantity.create(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new Offers(itemsWithQuantities);
    }

    public List<OfferWithQuantity> asList() { return offersWithQuantities; }
}
