package com.akikanellis.kata01.test_utils;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.ItemWithQuantity;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.offer.Offer;
import com.akikanellis.kata01.offer.OfferStrategies;
import com.akikanellis.kata01.offer.OfferStrategy;
import com.akikanellis.kata01.offer.OfferWithQuantity;
import com.akikanellis.kata01.offer.Offers;
import com.akikanellis.kata01.price.Price;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Fakes {

    private Fakes() { throw new AssertionError("No instances."); }

    public static Item createDefaultItem() {
        return Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ONE)
                .build();
    }

    public static Item.Builder createDefaultItemBuilder() {
        return Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ONE);
    }

    public static Items defaultItems() {
        Item apple = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item orange = Item.builder()
                .barcode(2)
                .name("Orange")
                .price(Price.ZERO)
                .build();
        Item pear = Item.builder()
                .barcode(3)
                .name("Pear")
                .price(Price.ZERO)
                .build();
        ItemWithQuantity appleWithQuantity = ItemWithQuantity.create(apple, 20);
        ItemWithQuantity orangeWithQuantity = ItemWithQuantity.create(orange, 60);
        ItemWithQuantity pearWithQuantity = ItemWithQuantity.create(pear, 8);

        List<ItemWithQuantity> itemsWithQuantities = new ArrayList<>();
        itemsWithQuantities.add(appleWithQuantity);
        itemsWithQuantities.add(orangeWithQuantity);
        itemsWithQuantities.add(pearWithQuantity);

        return Items.fromList(itemsWithQuantities);
    }

    public static OfferStrategy createDefaultOfferStrategy() {
        return new OfferStrategy() {
            @Override public Offers calculateOffers(Items items) {
                return null;
            }
        };
    }

    public static OfferStrategies createDefaultOfferStrategies() {
        OfferStrategy offerStrategy1 = items -> {
            if (items.asList().isEmpty()) return Offers.empty();

            OfferWithQuantity offerWithQuantity = OfferWithQuantity.create(createThreeApplesForOnePoundOffer(), 10);
            return Offers.fromList(Collections.singletonList(offerWithQuantity));
        };
        OfferStrategy offerStrategy2 = items -> {
            if (items.asList().isEmpty()) return Offers.empty();

            OfferWithQuantity offerWithQuantity = OfferWithQuantity.create(createTwoOrangesOneFreeOffer(), 2);
            return Offers.fromList(Collections.singletonList(offerWithQuantity));
        };
        OfferStrategy offerStrategy3 = items -> {
            if (items.asList().isEmpty()) return Offers.empty();

            OfferWithQuantity offerWithQuantity = OfferWithQuantity.create(createFiftyPercentOffPearsOffer(), 1);
            return Offers.fromList(Collections.singletonList(offerWithQuantity));
        };

        List<OfferStrategy> offerStrategies = new ArrayList<>();
        offerStrategies.add(offerStrategy1);
        offerStrategies.add(offerStrategy2);
        offerStrategies.add(offerStrategy3);

        return OfferStrategies.fromCollection(offerStrategies);
    }

    private static Offer createThreeApplesForOnePoundOffer() {
        return Offer.create("3 Apples for £1", Price.of(-50));
    }

    public static Offers createDefaultOffers() {
        return Offers.fromList(Arrays.asList(
                OfferWithQuantity.create(createThreeApplesForOnePoundOffer(), 10),
                OfferWithQuantity.create(createTwoOrangesOneFreeOffer(), 2),
                OfferWithQuantity.create(createFiftyPercentOffPearsOffer(), 1)
        ));
    }

    private static Offer createFiftyPercentOffPearsOffer() {
        return Offer.create("50% off pears", Price.of(-50));
    }

    private static Offer createTwoOrangesOneFreeOffer() {
        return Offer.create("Buy 2 Oranges get 1 free", Price.of(-50));
    }

    public static Offer createDefaultOffer() { return createThreeApplesForOnePoundOffer(); }
}

