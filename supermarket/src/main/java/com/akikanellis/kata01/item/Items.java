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
    private final List<QuantifiedItem> quantifiedItems;

    private Items(Collection<QuantifiedItem> quantifiedItems) {
        this.quantifiedItems = new ArrayList<>(quantifiedItems);
    }

    public static Items fromCollection(Collection<QuantifiedItem> itemsWithQuantities) {
        return new Items(itemsWithQuantities);
    }

    public static Items fromEntries(Set<Map.Entry<Item, Integer>> entries) {
        List<QuantifiedItem> itemsWithQuantities = entries.stream()
                .map(entry -> QuantifiedItem.create(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return fromCollection(itemsWithQuantities);
    }

    public static Items empty() { return fromCollection(Collections.emptyList()); }

    public boolean isEmpty() { return quantifiedItems.isEmpty(); }

    public List<QuantifiedItem> asList() { return new ArrayList<>(quantifiedItems); }

    public Stream<QuantifiedItem> stream() { return quantifiedItems.stream(); }
}
