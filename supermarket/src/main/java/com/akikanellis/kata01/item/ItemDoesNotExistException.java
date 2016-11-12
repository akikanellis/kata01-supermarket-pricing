package com.akikanellis.kata01.item;

public class ItemDoesNotExistException extends RuntimeException {
    public ItemDoesNotExistException(Item item) {
        super(String.format("The item does not exist. Item was [%s]", item));
    }

    public ItemDoesNotExistException(long barcode) {
        super(String.format("The item does not exist. Barcode was [%s]", barcode));
    }
}
