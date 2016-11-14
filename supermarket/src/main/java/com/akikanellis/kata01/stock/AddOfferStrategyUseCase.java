package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.OfferStrategyRepository;

/**
 * Adds a new offer strategy to the offer strategy repository.
 */
public class AddOfferStrategyUseCase {
    private final OfferStrategyRepository offerStrategies;

    public AddOfferStrategyUseCase(OfferStrategyRepository offerStrategies) { this.offerStrategies = offerStrategies; }

    public void execute(OfferStrategy offerStrategy) { offerStrategies.add(offerStrategy); }
}
