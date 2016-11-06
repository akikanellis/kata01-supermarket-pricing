package com.akikanellis.kata01;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Items {
    private final List<ItemWithQuantity> itemsWithQuantities;

    private Items(List<ItemWithQuantity> itemsWithQuantities) { this.itemsWithQuantities = itemsWithQuantities; }

    public static Items fromList(List<ItemWithQuantity> itemsWithQuantities) {
        return new Items(itemsWithQuantities);
    }

    public static Items fromEntries(Set<Map.Entry<Item, Integer>> entries) {
        List<ItemWithQuantity> itemsWithQuantities = entries.stream()
                .map(entry -> ItemWithQuantity.create(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new Items(itemsWithQuantities);
    }

    public List<ItemWithQuantity> asList() { return itemsWithQuantities; }
}
