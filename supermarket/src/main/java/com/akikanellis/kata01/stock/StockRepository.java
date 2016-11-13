package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;

/**
 * A repository holding {@link com.akikanellis.kata01.item.Item} elements.
 */
public interface StockRepository {

    /**
     * Returns if the repository contains a specified item.
     *
     * @param item the item to check
     * @return true if the repository contains the specified item, false if not
     */
    boolean contains(Item item);

    /**
     * Creates an item to the repository or throws an exception if it already exists.
     *
     * @param item the item to create
     * @throws com.akikanellis.kata01.item.ItemAlreadyExistsException if the item already exists in the repository
     */
    void create(Item item);

    /**
     * Replaces an item's quantity with the given one or throws an exception if the item does not exist.
     *
     * @param item the item to replace the quantity of
     * @throws com.akikanellis.kata01.item.ItemNotFoundException if the item does not exist in the repository
     */
    void replaceQuantity(Item item, int quantity);

    /**
     * Returns an item's quantity or throws an exception if the item does not exist.
     *
     * @param item the item to get the quantity of
     * @throws com.akikanellis.kata01.item.ItemNotFoundException if the item does not exist in the repository
     */
    int getQuantity(Item item);

    /**
     * Returns the item with the given barcode, or throws an exception.
     *
     * @param barcode the barcode of the item
     * @return the item if found
     * @throws com.akikanellis.kata01.item.ItemNotFoundException if there is no such item for the given barcode
     */
    Item getByBarcode(long barcode);

    /**
     * Returns all the items currently contained.
     *
     * @return all the items in the repository. If there are no items then this returns an empty representation
     */
    Items getAll();
}
