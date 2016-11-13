package com.akikanellis.kata01.item;

import com.akikanellis.kata01.price.Price;
import com.akikanellis.kata01.utils.Preconditions;
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
    public abstract static class Builder {
        public abstract Builder barcode(long barcode);

        public abstract Builder name(String name);

        public abstract Builder price(Price price);

        abstract Item autoBuild();

        public Item build() {
            Item item = autoBuild();

            Price price = item.price();
            Preconditions.checkArgument(!price.isNegative(), "Item price can't be negative. Was [%s]", price);

            return item;
        }
    }
}
