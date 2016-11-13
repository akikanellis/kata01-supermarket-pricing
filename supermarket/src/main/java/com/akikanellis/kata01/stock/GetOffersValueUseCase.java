package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.offer.QuantifiedOffer;
import com.akikanellis.kata01.price.Price;

/**
 * Returns the total value of the applicable offers.
 */
public class GetOffersValueUseCase {
    private final GetApplicableOffersUseCase getOffers;

    public GetOffersValueUseCase(GetApplicableOffersUseCase getOffers) { this.getOffers = getOffers; }

    public Price execute() {
        Offers offers = getOffers.execute();

        return offers.stream()
                .map(QuantifiedOffer::totalPrice)
                .reduce(Price::add)
                .orElse(Price.ZERO);
    }
}
