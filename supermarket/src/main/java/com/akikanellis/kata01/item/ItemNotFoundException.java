package com.akikanellis.kata01.item;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Item item) { super(String.format("The item was not found. Item was [%s]", item)); }

    public ItemNotFoundException(long barcode) {
        super(String.format("The item was not found. Barcode was [%s]", barcode));
    }
}
