package com.akikanellis.kata01;

import org.junit.Before;
import org.junit.Test;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StockRepositoryTest {
    private StockRepository stock;

    @Before public void beforeEach() { stock = new StockRepository(); }

    @Test public void creatingItem_withNotExistingItem_createsItem() {
        Item newItem = createDefaultItem();

        stock.create(newItem);

        assertThat(stock.contains(newItem)).isTrue();
    }

    @Test public void creatingItem_withNotExistingItem_initializesQuantityToZero() {
        Item item = createDefaultItem();

        stock.create(item);

        assertThat(stock.getQuantity(item)).isEqualTo(0);
    }

    @Test public void creatingItem_withExistingItem_throwsException() {
        Item item = createDefaultItem();
        stock.create(item);

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> stock.create(item));
    }

    @Test public void addingQuantity_withExistingItem_AddsToQuantity() throws ItemDoesNotExistException {
        Item item = createDefaultItem();
        stock.create(item);
        stock.addQuantity(item, 5);

        stock.addQuantity(item, 10);

        assertThat(stock.getQuantity(item)).isEqualTo(15);
    }

    @Test
    public void addingQuantity_withNotExistingItem_throwsException() throws ItemDoesNotExistException {
        Item item = createDefaultItem();

        assertThatExceptionOfType(ItemDoesNotExistException.class)
                .isThrownBy(() -> stock.addQuantity(item, 10));
    }

    @Test public void addingQuantity_withNegativeQuantity_throwsException() {
        Item item = createDefaultItem();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stock.addQuantity(item, -5));
    }

    @Test
    public void removingQuantity_withExistingItemAndBiggerCurrentQuantity_reducesQuantity() throws ItemDoesNotExistException {
        Item item = createDefaultItem();
        stock.create(item);
        stock.addQuantity(item, 20);

        stock.removeQuantity(item, 15);

        assertThat(stock.getQuantity(item)).isEqualTo(5);
    }

    @Test public void removingQuantity_withExistingItemAndSmallerCurrentQuantity_reducesQuantityToZero()
            throws ItemDoesNotExistException {
        Item item = createDefaultItem();
        stock.create(item);
        stock.addQuantity(item, 10);

        stock.removeQuantity(item, 15);

        assertThat(stock.getQuantity(item)).isEqualTo(0);
    }

    @Test public void removingQuantity_withNotExistingItem_throwsException()
            throws ItemDoesNotExistException {
        Item item = createDefaultItem();

        assertThatExceptionOfType(ItemDoesNotExistException.class)
                .isThrownBy(() -> stock.removeQuantity(item, 10));
    }

    @Test public void removingQuantity_withNegativeQuantity_throwsException() {
        Item item = createDefaultItem();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stock.removeQuantity(item, -5));
    }
}