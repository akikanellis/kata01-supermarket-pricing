package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.OfferStrategyRepository;

public class FindOfferStrategyByIdUseCase {
    private final OfferStrategyRepository offerStrategyRepository;

    public FindOfferStrategyByIdUseCase(OfferStrategyRepository offerStrategyRepository) {
        this.offerStrategyRepository = offerStrategyRepository;
    }

    public OfferStrategy execute(long id) { return offerStrategyRepository.getById(id); }
}
