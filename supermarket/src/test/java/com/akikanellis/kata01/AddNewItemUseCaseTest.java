package com.akikanellis.kata01;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddNewItemUseCaseTest {
    @Mock private StockRepository stock;
    private AddNewItemUseCase addNewItemUseCase;

    @Before public void beforeEach() { addNewItemUseCase = new AddNewItemUseCase(stock); }

}