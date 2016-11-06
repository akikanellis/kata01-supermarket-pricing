package com.akikanellis.kata01.test_utils;

import com.akikanellis.kata01.Item;
import com.akikanellis.kata01.Price;

public class Fakes {

    public static Item createDefaultItem() {
        return Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ONE)
                .build();
    }
}
