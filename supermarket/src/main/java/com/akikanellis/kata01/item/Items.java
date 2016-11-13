package com.akikanellis.kata01.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Items {
    private final List<ItemWithQuantity> itemsWithQuantities;

    private Items(Collection<ItemWithQuantity> itemsWithQuantities) {
        this.itemsWithQuantities = new ArrayList<>(itemsWithQuantities);
    }

    public static Items fromCollection(Collection<ItemWithQuantity> itemsWithQuantities) {
        return new Items(itemsWithQuantities);
    }

    public static Items fromEntries(Set<Map.Entry<Item, Integer>> entries) {
        List<ItemWithQuantity> itemsWithQuantities = entries.stream()
                .map(entry -> ItemWithQuantity.create(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return fromCollection(itemsWithQuantities);
    }

    public static Items empty() { return fromCollection(Collections.emptyList()); }

    public boolean isEmpty() { return itemsWithQuantities.isEmpty(); }

    public List<ItemWithQuantity> asList() { return new ArrayList<>(itemsWithQuantities); }

    public Stream<ItemWithQuantity> stream() { return itemsWithQuantities.stream(); }
}
