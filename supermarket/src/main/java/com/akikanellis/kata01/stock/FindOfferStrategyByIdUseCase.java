package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.OfferStrategyRepository;

/**
 * Finds an offer strategy by its id.
 */
public class FindOfferStrategyByIdUseCase {
    private final OfferStrategyRepository offerStrategies;

    public FindOfferStrategyByIdUseCase(OfferStrategyRepository offerStrategies) {
        this.offerStrategies = offerStrategies;
    }

    public OfferStrategy execute(long id) { return offerStrategies.getById(id); }
}
