package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.item.QuantifiedItem;
import com.akikanellis.kata01.offer.Offer;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.offer.QuantifiedOffer;
import com.akikanellis.kata01.price.Price;

/**
 * An example offer strategy used for testing where all of our items are discounted by 10%.
 */
public class TenPercentOffEverything extends OfferStrategy {
    private static final double TEN_PERCENT = 0.10;

    private TenPercentOffEverything(long id) { super(id, "10% off everything"); }

    public static OfferStrategy create(int id) { return new TenPercentOffEverything(id); }

    @Override public Offers calculateOffers(Items items) {
        Price itemsTotalPrice = calculateItemsTotalPrice(items);
        Price discount = itemsTotalPrice.multiplyBy(TEN_PERCENT).negate();

        Offer offer = Offer.create(description(), discount);
        QuantifiedOffer quantifiedOffer = QuantifiedOffer.create(offer, 1);

        return Offers.fromSingle(quantifiedOffer);
    }

    private Price calculateItemsTotalPrice(Items items) {
        return items.stream()
                .map(QuantifiedItem::totalPrice)
                .reduce(Price::add)
                .orElse(Price.ZERO);
    }
}
