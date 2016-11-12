package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.OfferWithQuantity;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.price.Price;

public class GetOffersValueUseCase {
    private final GetApplicableOffersUseCase getOffers;

    public GetOffersValueUseCase(GetApplicableOffersUseCase getOffers) { this.getOffers = getOffers; }

    public Price execute() {
        Offers offers = getOffers.execute();

        return offers.asList().stream()
                .map(OfferWithQuantity::totalPrice)
                .reduce(Price::add)
                .orElse(Price.ZERO);
    }
}
