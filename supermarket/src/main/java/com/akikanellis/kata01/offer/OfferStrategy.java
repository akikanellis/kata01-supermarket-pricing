package com.akikanellis.kata01.offer;

import com.akikanellis.kata01.item.Items;

/**
 * A strategy that determines how the offers are calculated. Each {@code OfferStrategy} is uniquely identified by its
 * id.
 */
public abstract class OfferStrategy {
    private final long id;
    private final String description;

    protected OfferStrategy(long id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * @return the unique id of the {@code OfferStrategy}
     */
    public final long id() { return id; }

    /**
     * @return the description of the {@code OfferStrategy}
     */
    public final String description() { return description; }

    /**
     * Calculates the applicable offers on the items according to the provided algorithm.
     *
     * @return the calculated offers
     */
    public abstract Offers calculateOffers(Items items);

    @Override public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfferStrategy)) return false;

        OfferStrategy other = (OfferStrategy) o;
        return id == other.id;
    }

    @Override public final int hashCode() { return Long.hashCode(id); }

    @Override public String toString() {
        return "OfferStrategy{"
                + "id=" + id + ", "
                + "description='" + description + '\''
                + '}';
    }
}
