package com.akikanellis.kata01.offer;

import com.akikanellis.kata01.item.Items;

public interface OfferStrategy {

    Offers calculateOffers(Items items);
}
