package com.akikanellis.kata01.item;

import com.akikanellis.kata01.Price;
import com.google.auto.value.AutoValue;

import java.util.Objects;

@AutoValue
public abstract class Item {

    public static Builder builder() { return new AutoValue_Item.Builder(); }

    public abstract long barcode();

    public abstract String name();

    public abstract Price price();

    @Override public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item other = (Item) o;
        return Objects.equals(barcode(), other.barcode());
    }

    @Override public final int hashCode() { return Long.hashCode(barcode()); }

    @AutoValue.Builder
    public interface Builder {
        Builder barcode(long barcode);

        Builder name(String name);

        Builder price(Price price);

        Item build();
    }
}
