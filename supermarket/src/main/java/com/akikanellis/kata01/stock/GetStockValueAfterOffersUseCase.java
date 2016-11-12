package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.price.Price;

public class GetStockValueAfterOffersUseCase {
    private final GetStockValueBeforeOffersUseCase getStockValueBeforeOffers;
    private final GetOffersValueUseCase getOffersValue;

    public GetStockValueAfterOffersUseCase(GetStockValueBeforeOffersUseCase getStockValueBeforeOffers,
                                           GetOffersValueUseCase getOffersValue) {
        this.getStockValueBeforeOffers = getStockValueBeforeOffers;
        this.getOffersValue = getOffersValue;
    }

    public Price execute() {
        Price stockValueBeforeOffers = getStockValueBeforeOffers.execute();
        Price offersValue = getOffersValue.execute();

        return stockValueBeforeOffers.equals(Price.ZERO) ? Price.ZERO : stockValueBeforeOffers.add(offersValue);
    }
}
