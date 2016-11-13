package com.akikanellis.kata01.item;

import com.akikanellis.kata01.price.Price;
import org.junit.Test;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItemBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class QuantifiedItemTest {

    @Test public void totalPrice_isMultiplicationOfPriceAndQuantity() {
        Item item = createDefaultItemBuilder()
                .price(Price.of(10))
                .build();

        QuantifiedItem quantifiedItem = QuantifiedItem.create(item, 15);

        assertThat(quantifiedItem.totalPrice()).isEqualTo(Price.of(150));
    }
}