package com.akikanellis.kata01.offer;

public class OfferStrategyNotFoundException extends RuntimeException {

    public OfferStrategyNotFoundException(long id) { super(String.format("The item does not exist. Id was [%s]", id)); }
}
