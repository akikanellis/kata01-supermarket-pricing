package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.ItemAlreadyExistsException;
import com.akikanellis.kata01.item.ItemDoesNotExistException;
import com.akikanellis.kata01.item.Items;

import java.util.HashMap;
import java.util.Map;


class InMemoryStockRepository implements StockRepository {
    private final Map<Item, Integer> stock;

    InMemoryStockRepository() { this.stock = new HashMap<>(); }

    @Override public boolean contains(Item item) { return stock.containsKey(item); }

    @Override public void create(Item item) {
        if (contains(item)) throw new ItemAlreadyExistsException(item);

        stock.put(item, 0);
    }

    @Override public void replaceQuantity(Item item, int quantity) {
        if (!contains(item)) throw new ItemDoesNotExistException(item);

        stock.put(item, quantity);
    }

    @Override public int getQuantity(Item item) { return stock.get(item); }

    @Override public Items getAll() { return Items.fromEntries(stock.entrySet()); }
}
