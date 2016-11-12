package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategyRepository;
import com.akikanellis.kata01.offer.Offers;

public class GetActiveOffersUseCase {
    private final StockRepository stock;
    private final OfferStrategyRepository offerStrategyRepository;

    public GetActiveOffersUseCase(StockRepository stock, OfferStrategyRepository offerStrategyRepository) {
        this.stock = stock;
        this.offerStrategyRepository = offerStrategyRepository;
    }

    public Offers execute() {
        OfferStrategies offerStrategies = offerStrategyRepository.getAll();
        Items items = stock.getAll();

        return offerStrategies.stream()
                .map(strategy -> strategy.calculateOffers(items))
                .reduce(Offers::join)
                .orElse(Offers.empty());
    }
}
