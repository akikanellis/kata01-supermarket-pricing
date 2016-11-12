package com.akikanellis.kata01.offer;

public interface OfferStrategyRepository {

    void add(OfferStrategy offerStrategy);

    void remove(OfferStrategy offerStrategy);

    OfferStrategies getAll();
}
