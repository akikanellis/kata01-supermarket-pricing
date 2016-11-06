package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.Items;
import com.akikanellis.kata01.Offers;

public interface OfferStrategy {

    Offers calculateOffers(Items items);
}
