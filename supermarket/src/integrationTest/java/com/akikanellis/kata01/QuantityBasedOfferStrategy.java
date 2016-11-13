package com.akikanellis.kata01;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.item.QuantifiedItem;
import com.akikanellis.kata01.offer.Offer;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.offer.QuantifiedOffer;
import com.akikanellis.kata01.price.Price;

public class QuantityBasedOfferStrategy extends OfferStrategy {
    private final Item applicableItem;
    private final int quantityToDiscount;
    private final Price discount;

    protected QuantityBasedOfferStrategy(long id, String description, Item applicableItem, int quantityToDiscount,
                                         Price discount) {
        super(id, description);
        this.applicableItem = applicableItem;
        this.quantityToDiscount = quantityToDiscount;
        this.discount = discount;
    }

    public static QuantityBasedOfferStrategy create(long id, Item applicableItem, int quantityToDiscount, Price discount) {
        Price discountInPounds = discount.multiplyBy(quantityToDiscount).divideBy(1000);
        String description = String.format("%d %s for £%s",
                quantityToDiscount,
                applicableItem.name(),
                discountInPounds);

        return new QuantityBasedOfferStrategy(id, description, applicableItem, quantityToDiscount, discount);
    }

    @Override public Offers calculateOffers(Items items) {
        int numberOfApplicableItems = items.stream()
                .filter(quantifiedItem -> quantifiedItem.item().equals(applicableItem))
                .map(QuantifiedItem::quantity)
                .reduce((firstQuantity, secondQuantity) -> firstQuantity + secondQuantity)
                .orElse(0);
        int timesToApplyOffer = numberOfApplicableItems / quantityToDiscount;

        Offer baseOffer = Offer.create(description(), discount);
        QuantifiedOffer quantifiedOffer = QuantifiedOffer.create(baseOffer, timesToApplyOffer);

        return Offers.fromSingle(quantifiedOffer);
    }
}
