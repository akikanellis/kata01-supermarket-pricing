package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.Offers;
import com.akikanellis.kata01.item.Items;

public interface OfferStrategy {

    Offers calculateOffers(Items items);
}
