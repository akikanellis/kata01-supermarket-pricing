package com.akikanellis.kata01;

public class RemoveOfferStrategyUseCase {
    private final OfferStrategyRepository offerStrategies;

    public RemoveOfferStrategyUseCase(OfferStrategyRepository offerStrategies) { this.offerStrategies = offerStrategies; }

    public void execute(OfferStrategy offerStrategy) { offerStrategies.remove(offerStrategy); }
}
