package com.akikanellis.kata01.stock;

public class GetActiveOfferStrategiesUseCase {
    private final OfferStrategyRepository offerStrategies;

    public GetActiveOfferStrategiesUseCase(OfferStrategyRepository offerStrategies) {
        this.offerStrategies = offerStrategies;
    }

    public OfferStrategies execute() { return offerStrategies.getAll(); }
}
