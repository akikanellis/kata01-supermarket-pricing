package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;

public class FindItemByBarcodeUseCase {
    private final StockRepository stock;

    public FindItemByBarcodeUseCase(StockRepository stock) { this.stock = stock; }

    public Item execute(long barcode) { return stock.getByBarcode(barcode); }
}
