package com.akikanellis.kata01.offer;

import com.akikanellis.kata01.price.Price;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Offer {

    public static Offer create(String description, Price price) { return new AutoValue_Offer(description, price); }

    public abstract String description();

    public abstract Price price();
}
