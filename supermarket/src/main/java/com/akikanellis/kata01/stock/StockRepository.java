package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;

public interface StockRepository {

    boolean contains(Item item);

    void create(Item item);

    void replaceQuantity(Item item, int quantity);

    int getQuantity(Item item);

    Items getAll();
}
