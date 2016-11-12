package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.Item;
import com.akikanellis.kata01.ItemDoesNotExistException;
import com.akikanellis.kata01.Items;

import java.util.HashMap;
import java.util.Map;


public class StockRepository {
    private final Map<Item, Integer> stock;

    public StockRepository() { this.stock = new HashMap<>(); }

    public void create(Item item) {
        if (contains(item)) {
            throw new IllegalStateException(String.format("The item already exists. Item was [%s]", item));
        }

        stock.put(item, 0);
    }

    public void replaceQuantity(Item item, int quantity) {
        checkItemExists(item);

        stock.put(item, quantity);
    }

    private void checkItemExists(Item item) {
        if (!contains(item)) throw new ItemDoesNotExistException(item);
    }

    /* Visible for testing */
    boolean contains(Item item) { return stock.containsKey(item); }

    /* Visible for testing */
    int getQuantity(Item item) { return stock.get(item); }

    public Items getAll() { return Items.fromEntries(stock.entrySet()); }
}