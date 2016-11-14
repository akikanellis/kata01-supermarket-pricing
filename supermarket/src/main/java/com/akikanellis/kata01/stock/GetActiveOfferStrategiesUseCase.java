package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategyRepository;

/**
 * Returns all the currently active/enabled offer strategies.
 */
public class GetActiveOfferStrategiesUseCase {
    private final OfferStrategyRepository offerStrategies;

    public GetActiveOfferStrategiesUseCase(OfferStrategyRepository offerStrategies) {
        this.offerStrategies = offerStrategies;
    }

    public OfferStrategies execute() { return offerStrategies.getAll(); }
}
