package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.ItemWithQuantity;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.price.Price;

public class GetStockValueBeforeOffersUseCase {
    private final GetStockUseCase getStock;

    public GetStockValueBeforeOffersUseCase(GetStockUseCase getStock) { this.getStock = getStock; }

    public Price execute() {
        Items items = getStock.execute();

        return items.stream()
                .map(ItemWithQuantity::totalPrice)
                .reduce(Price::add)
                .orElse(Price.ZERO);
    }
}
