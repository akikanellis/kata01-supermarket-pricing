package com.akikanellis.kata01.offer;

public interface OfferStrategyRepository {

    boolean contains(OfferStrategy offerStrategy);

    void add(OfferStrategy offerStrategy);

    void remove(OfferStrategy offerStrategy);

    OfferStrategy getById(long id);

    OfferStrategies getAll();
}
