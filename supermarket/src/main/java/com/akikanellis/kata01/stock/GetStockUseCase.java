package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Items;

/**
 * Returns all the currently available item stock.
 */
public class GetStockUseCase {
    private final StockRepository stock;

    public GetStockUseCase(StockRepository stock) { this.stock = stock; }

    public Items execute() { return stock.getAll(); }
}
