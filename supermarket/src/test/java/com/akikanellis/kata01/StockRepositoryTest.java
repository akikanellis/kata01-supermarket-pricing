package com.akikanellis.kata01;

import org.junit.Before;
import org.junit.Test;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StockRepositoryTest {
    private StockRepository stock;

    @Before public void beforeEach() { stock = new StockRepository(); }

    @Test public void creatingItem_withNotExistingItem_initializesQuantityToZero() {
        Item item = createDefaultItem();

        stock.create(item);

        assertThat(stock.getQuantity(item)).isEqualTo(0);
    }

    @Test public void creatingItem_withNotExistingItem_createsItem() {
        Item newItem = createDefaultItem();

        stock.create(newItem);

        assertThat(stock.contains(newItem)).isTrue();
    }

    @Test public void creatingItem_withExistingItem_doesNothing() {
        Item item = createDefaultItem();
        stock.increaseQuantity(item, 5);

        stock.create(item);

        assertThat(stock.getQuantity(item)).isEqualTo(5);
    }

    @Test public void addingItem_withExistingItem_AddsToQuantity() {
        Item item = createDefaultItem();
        stock.increaseQuantity(item, 5);

        stock.increaseQuantity(item, 10);

        assertThat(stock.getQuantity(item)).isEqualTo(15);
    }

    @Test public void addingItem_withNotExistingItem_createsItemAndInitializesToQuantity() {
        Item item = createDefaultItem();

        stock.increaseQuantity(item, 10);

        assertThat(stock.getQuantity(item)).isEqualTo(10);
    }

    @Test public void addingItem_withNegativeQuantity_throwsException() {
        Item item = createDefaultItem();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stock.increaseQuantity(item, -5));
    }

    @Test public void reducingQuantity_withExistingItemAndBiggerCurrentQuantity_reducesQuantity() {
        Item item = createDefaultItem();
        stock.increaseQuantity(item, 20);

        stock.decreaseQuantity(item, 15);

        assertThat(stock.getQuantity(item)).isEqualTo(5);
    }

    @Test public void reducingQuantity_withExistingItemAndSmallerCurrentQuantity_reducesQuantityToZero() {
        Item item = createDefaultItem();
        stock.increaseQuantity(item, 10);

        stock.decreaseQuantity(item, 15);

        assertThat(stock.getQuantity(item)).isEqualTo(0);
    }

    @Test public void reducingQuantity_withNotExistingItem_createsItemWithZeroQuantity() {
        Item item = createDefaultItem();

        stock.decreaseQuantity(item, 10);

        assertThat(stock.getQuantity(item)).isEqualTo(0);
    }

    @Test public void reducingQuantity_withNegativeQuantity_throwsException() {
        Item item = createDefaultItem();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stock.decreaseQuantity(item, -5));
    }
}