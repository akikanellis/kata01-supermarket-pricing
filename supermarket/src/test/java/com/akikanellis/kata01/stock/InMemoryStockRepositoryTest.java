package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.ItemAlreadyExistsException;
import com.akikanellis.kata01.item.ItemNotFoundException;
import com.akikanellis.kata01.item.Items;
import org.junit.Before;
import org.junit.Test;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItem;
import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItemBuilder;
import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItems;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class InMemoryStockRepositoryTest {
    private InMemoryStockRepository stock;

    @Before public void beforeEach() { stock = new InMemoryStockRepository(); }

    @Test public void creatingItem_withNotExistingItem_createsItem() {
        Item item = createDefaultItem();

        stock.create(item);

        assertThat(stock.contains(item)).isTrue();
    }

    @Test public void creatingItem_withNotExistingItem_initializesQuantityToZero() {
        Item item = createDefaultItem();

        stock.create(item);

        assertThat(stock.getQuantity(item)).isEqualTo(0);
    }

    @Test public void creatingItem_withExistingItem_throwsException() {
        Item item = createDefaultItem();
        stock.create(item);

        assertThatExceptionOfType(ItemAlreadyExistsException.class)
                .isThrownBy(() -> stock.create(item));
    }

    @Test public void replacingQuantity_withExistingItem_replacesQuantity() {
        Item item = createDefaultItem();
        stock.create(item);

        stock.replaceQuantity(item, 10);

        assertThat(stock.getQuantity(item)).isEqualTo(10);
    }

    @Test public void replacingQuantity_withNotExistingItem_throwsException() {
        assertThatExceptionOfType(ItemNotFoundException.class)
                .isThrownBy(() -> stock.replaceQuantity(createDefaultItem(), 10));
    }

    @Test public void gettingAllItems_withItems_returnsAllPackagedAsItems() {
        Items expectedItems = createDefaultItems();
        expectedItems.stream().forEach(itemWithQuantity -> {
            stock.create(itemWithQuantity.item());
            stock.replaceQuantity(itemWithQuantity.item(), itemWithQuantity.quantity());
        });

        Items actualItems = stock.getAll();

        assertThat(actualItems.asList()).containsOnlyElementsOf(expectedItems.asList());
    }

    @Test public void gettingAllItems_withNoItems_returnsEmptyItems() {
        Items items = stock.getAll();

        assertThat(items.isEmpty()).isTrue();
    }

    @Test public void gettingItemByBarcode_withItemPresent_returnsItem() {
        Item expectedItem = createDefaultItemBuilder()
                .barcode(10)
                .build();
        stock.create(expectedItem);

        Item actualItem = stock.getByBarcode(10);

        assertThat(actualItem).isSameAs(expectedItem);
    }

    @Test public void gettingItemByBarcode_withNoItemPresent_throwsException() {
        assertThatExceptionOfType(ItemNotFoundException.class)
                .isThrownBy(() -> stock.getByBarcode(1));
    }
}