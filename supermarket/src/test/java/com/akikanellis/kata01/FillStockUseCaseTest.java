package com.akikanellis.kata01;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FillStockUseCaseTest {
    @Mock private StockRepository stock;
    @Mock private AddNewItemIfNotExistsUseCase addNewItemIfNotExists;
    private FillStockUseCase fillStock;

    @Before public void beforeEach() { fillStock = new FillStockUseCase(stock, addNewItemIfNotExists); }
}