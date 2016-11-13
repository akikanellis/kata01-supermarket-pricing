package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.Offer;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.offer.QuantifiedOffer;
import com.akikanellis.kata01.price.Price;

public class TenPercentOffEverything extends OfferStrategy {

    private TenPercentOffEverything(long id) { super(id, "10% off everything"); }

    public static OfferStrategy create(int id) { return new TenPercentOffEverything(id); }

    @Override public Offers calculateOffers(Items items) {
        Price itemsPrice = items.stream()
                .map(quantifiedItem -> quantifiedItem.item().price())
                .reduce(Price::add)
                .orElse(Price.ZERO);

        Price discount = itemsPrice.multiplyBy(0.10).negate();
        Offer offer = Offer.create(description(), discount);
        QuantifiedOffer quantifiedOffer = QuantifiedOffer.create(offer, 1);

        return Offers.fromSingle(quantifiedOffer);
    }
}
