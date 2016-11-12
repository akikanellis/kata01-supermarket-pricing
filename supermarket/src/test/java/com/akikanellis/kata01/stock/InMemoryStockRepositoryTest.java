package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.ItemAlreadyExistsException;
import com.akikanellis.kata01.item.ItemDoesNotExistException;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.test_utils.Fakes;
import org.junit.Before;
import org.junit.Test;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class InMemoryStockRepositoryTest {
    private InMemoryStockRepository stock;

    @Before public void beforeEach() { stock = new InMemoryStockRepository(); }

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

        assertThatExceptionOfType(ItemAlreadyExistsException.class)
                .isThrownBy(() -> stock.create(item));
    }

    @Test public void replacingQuantity_withExistingItem_ReplacesQuantity() {
        Item item = createDefaultItem();
        stock.create(item);

        stock.replaceQuantity(item, 10);

        assertThat(stock.getQuantity(item)).isEqualTo(10);
    }

    @Test public void replacingQuantity_withNotExistingItem_throwsException() {
        Item item = createDefaultItem();

        assertThatExceptionOfType(ItemDoesNotExistException.class)
                .isThrownBy(() -> stock.replaceQuantity(item, 10));
    }

    @Test public void gettingAllItems_withItems_returnsAllPackagedAsItems() {
        Items expectedItems = Fakes.defaultItems();
        expectedItems.asList().forEach(itemWithQuantity -> {
            stock.create(itemWithQuantity.item());
            stock.replaceQuantity(itemWithQuantity.item(), itemWithQuantity.quantity());
        });

        Items actualItems = stock.getAll();

        assertThat(actualItems.asList()).containsOnlyElementsOf(expectedItems.asList());
    }

    @Test public void gettingAllItems_withNoItems_returnsEmptyItems() {
        Items items = stock.getAll();

        assertThat(items.asList()).isEmpty();
    }
}