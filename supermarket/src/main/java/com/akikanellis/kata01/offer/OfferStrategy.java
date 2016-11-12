package com.akikanellis.kata01.offer;

import com.akikanellis.kata01.item.Items;

public abstract class OfferStrategy {
    private final long id;
    private final String description;

    protected OfferStrategy(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public final long id() { return id; }

    public final String description() { return description; }

    public abstract Offers calculateOffers(Items items);

    @Override public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfferStrategy)) return false;

        OfferStrategy other = (OfferStrategy) o;
        return id == other.id;
    }

    @Override public final int hashCode() { return Long.hashCode(id); }
}
