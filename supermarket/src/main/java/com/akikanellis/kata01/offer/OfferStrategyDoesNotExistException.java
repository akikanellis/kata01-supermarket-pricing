package com.akikanellis.kata01.offer;

public class OfferStrategyDoesNotExistException extends RuntimeException {

    public OfferStrategyDoesNotExistException(OfferStrategy offerStrategy) {
        super(String.format("The offer strategy does not exist. Offer strategy was [%s]", offerStrategy));
    }

    public OfferStrategyDoesNotExistException(long id) {
        super(String.format("The item does not exist. Id was [%s]", id));
    }
}
