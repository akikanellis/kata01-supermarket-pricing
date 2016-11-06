package com.akikanellis.kata01;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.akikanellis.kata01.test_utils.Fakes.createDefaultItem;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddNewItemUseCaseTest {
    @Mock private StockRepository stock;
    private AddNewItemUseCase addNewItemUseCase;

    @Before public void beforeEach() { addNewItemUseCase = new AddNewItemUseCase(stock); }

    @Test public void executing_createsNewItemInRepository() {
        Item item = createDefaultItem();

        addNewItemUseCase.execute(item);

        verify(stock).create(item);
    }
}