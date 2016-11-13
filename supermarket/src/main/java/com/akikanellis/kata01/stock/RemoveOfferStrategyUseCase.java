package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.OfferStrategyRepository;

/**
 * Removes an offer strategy from the offer strategy repository.
 */
public class RemoveOfferStrategyUseCase {
    private final OfferStrategyRepository offerStrategies;

    public RemoveOfferStrategyUseCase(OfferStrategyRepository offerStrategies) {
        this.offerStrategies = offerStrategies;
    }

    public void execute(OfferStrategy offerStrategy) { offerStrategies.remove(offerStrategy); }
}
