package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.Offers;

public interface OfferStrategy {

    Offers calculateOffers(Items items);
}
