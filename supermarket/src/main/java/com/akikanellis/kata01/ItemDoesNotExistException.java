package com.akikanellis.kata01;

public class ItemDoesNotExistException extends RuntimeException {
    public ItemDoesNotExistException(Item item) {
        super(String.format("The item does not exist. Item was [%s]", item));
    }
}
