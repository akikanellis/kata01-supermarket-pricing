package com.akikanellis.kata01.item;

import com.akikanellis.kata01.price.Price;
import org.junit.Test;

import java.util.stream.IntStream;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItemBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ItemTest {

    @Test public void creating_withPositivePrice_creates() {
        createDefaultItemBuilder()
                .price(Price.of(50))
                .build();
    }

    @Test public void creating_withZeroPrice_creates() {
        createDefaultItemBuilder()
                .price(Price.ZERO)
                .build();
    }

    @Test public void creating_withNegativePrice_throwsException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        createDefaultItemBuilder()
                                .price(Price.of(-1))
                                .build()
                );
    }

    @Test public void comparingEquality_withEverythingEqual_areEqual() {
        Item first = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item second = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();

        assertThat(first).isEqualTo(second);
    }

    @Test public void comparingEquality_withNothingEqual_areNotEqual() {
        Item first = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item second = Item.builder()
                .barcode(2)
                .name("Orange")
                .price(Price.ONE)
                .build();

        assertThat(first).isNotEqualTo(second);
    }

    @Test public void comparingEquality_withEverythingEqualExceptBarcodes_areNotEqual() {
        Item first = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item second = Item.builder()
                .barcode(2)
                .name("Apple")
                .price(Price.ZERO)
                .build();

        assertThat(first).isNotEqualTo(second);
    }

    @Test public void comparingEquality_withOnlyBarcodesEqual_areEqual() {
        Item first = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item second = Item.builder()
                .barcode(1)
                .name("Orange")
                .price(Price.ONE)
                .build();

        assertThat(first).isEqualTo(second);
    }

    @Test public void equals_hasEqualHashcodes() {
        Item first = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item second = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();

        assertThat(first.hashCode()).isEqualTo(second.hashCode());
    }

    @Test public void equals_isReflexive() {
        Item item = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();

        assertThat(item).isEqualTo(item);
    }

    @Test public void equals_isSymmetric() {
        Item first = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item second = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();

        assertThat(first).isEqualTo(second);
        assertThat(second).isEqualTo(first);
    }

    @Test public void equals_isTransitive() {
        Item first = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item second = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item third = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();

        assertThat(first).isEqualTo(second);
        assertThat(second).isEqualTo(third);
        assertThat(first).isEqualTo(third);
    }

    @Test public void equals_isConsistent() {
        Item first = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item second = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();

        IntStream.range(0, 5)
                .forEach(ignored -> assertThat(first).isEqualTo(second));
    }

    @Test public void equals_withNull_areNotEqual() {
        Item item = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();

        assertThat(item).isNotEqualTo(null);
    }
}