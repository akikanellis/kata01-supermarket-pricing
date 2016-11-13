package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.item.QuantifiedItem;
import com.akikanellis.kata01.price.Price;

/**
 * Returns the stock value in its "pure" form, before removing the offers value.
 */
public class GetStockValueBeforeOffersUseCase {
    private final GetStockUseCase getStock;

    public GetStockValueBeforeOffersUseCase(GetStockUseCase getStock) { this.getStock = getStock; }

    public Price execute() {
        Items items = getStock.execute();

        return items.stream()
                .map(QuantifiedItem::totalPrice)
                .reduce(Price::add)
                .orElse(Price.ZERO);
    }
}
