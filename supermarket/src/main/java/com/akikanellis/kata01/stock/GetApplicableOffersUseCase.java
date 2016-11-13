package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategyRepository;
import com.akikanellis.kata01.offer.Offers;

public class GetApplicableOffersUseCase {
    private final StockRepository stock;
    private final OfferStrategyRepository offerStrategies;

    public GetApplicableOffersUseCase(StockRepository stock, OfferStrategyRepository offerStrategies) {
        this.stock = stock;
        this.offerStrategies = offerStrategies;
    }

    public Offers execute() {
        OfferStrategies strategies = offerStrategies.getAll();
        Items items = stock.getAll();

        return strategies.stream()
                .map(strategy -> strategy.calculateOffers(items))
                .reduce(Offers::join)
                .orElse(Offers.empty());
    }
}
