package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.item.QuantifiedItem;
import com.akikanellis.kata01.offer.Offer;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.offer.QuantifiedOffer;
import com.akikanellis.kata01.price.Price;

/**
 * A quantity-based offer strategy uses the item's base price and quantity of baseDiscount to calculate the final
 * discount.
 */
class QuantityBasedOfferStrategy extends OfferStrategy {
    private final Item applicableItem;
    private final int quantityToDiscount;
    private final Price baseDiscount;

    QuantityBasedOfferStrategy(long id, String description, Item applicableItem, int quantityToDiscount,
                               Price baseDiscount) {
        super(id, description);
        this.applicableItem = applicableItem;
        this.quantityToDiscount = quantityToDiscount;
        this.baseDiscount = baseDiscount;
    }

    @Override public Offers calculateOffers(Items items) {
        int quantityOfApplicableItems = getQuantityOfApplicableItems(items);
        int timesToApplyOffer = quantityOfApplicableItems / quantityToDiscount;

        Offer baseOffer = Offer.create(description(), baseDiscount);
        QuantifiedOffer quantifiedOffer = QuantifiedOffer.create(baseOffer, timesToApplyOffer);

        return Offers.fromSingle(quantifiedOffer);
    }

    private int getQuantityOfApplicableItems(Items items) {
        return items.stream()
                .filter(quantifiedItem -> quantifiedItem.item().equals(applicableItem))
                .map(QuantifiedItem::quantity)
                .reduce((firstQuantity, secondQuantity) -> firstQuantity + secondQuantity)
                .orElse(0);
    }
}
