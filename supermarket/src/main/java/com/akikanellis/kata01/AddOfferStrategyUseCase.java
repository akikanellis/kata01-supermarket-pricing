package com.akikanellis.kata01;

public class AddOfferStrategyUseCase {
    private final OfferStrategyRepository offerStrategies;

    public AddOfferStrategyUseCase(OfferStrategyRepository offerStrategies) { this.offerStrategies = offerStrategies; }

    public void execute(OfferStrategy offerStrategy) { offerStrategies.add(offerStrategy); }
}
