package com.akikanellis.kata01.offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Offers {
    private final List<OfferWithQuantity> offersWithQuantities;

    private Offers(List<OfferWithQuantity> offersWithQuantities) { this.offersWithQuantities = offersWithQuantities; }

    public static Offers fromList(List<OfferWithQuantity> itemsWithQuantities) {
        return new Offers(itemsWithQuantities);
    }

    public static Offers empty() { return new Offers(Collections.emptyList()); }

    public List<OfferWithQuantity> asList() { return new ArrayList<>(offersWithQuantities); }

    public Offers join(Offers other) {
        List<OfferWithQuantity> joinedOfferWithQuantities = new ArrayList<>();

        List<OfferWithQuantity> otherOfferWithQuantities = other.asList();
        joinedOfferWithQuantities.addAll(offersWithQuantities);
        joinedOfferWithQuantities.addAll(otherOfferWithQuantities);

        return Offers.fromList(joinedOfferWithQuantities);
    }
}