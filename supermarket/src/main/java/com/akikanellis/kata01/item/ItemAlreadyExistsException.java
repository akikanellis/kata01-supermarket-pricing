package com.akikanellis.kata01.item;

public class ItemAlreadyExistsException extends RuntimeException {
    public ItemAlreadyExistsException(Item item) {
        super(String.format("The item already exists. Item was [%s]", item));
    }
}
