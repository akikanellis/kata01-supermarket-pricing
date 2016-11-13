package com.akikanellis.kata01.test_utils;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.Items;
import com.akikanellis.kata01.item.QuantifiedItem;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Fakes {

    private Fakes() { throw new AssertionError("No instances."); }

    public static Item createDefaultItem() {
        return Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ONE)
                .build();
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
        QuantifiedItem appleWithQuantity = QuantifiedItem.create(apple, 20);
        QuantifiedItem orangeWithQuantity = QuantifiedItem.create(orange, 60);
        QuantifiedItem pearWithQuantity = QuantifiedItem.create(pear, 8);

        List<QuantifiedItem> quantifiedItems = new ArrayList<>();
        quantifiedItems.add(appleWithQuantity);
        quantifiedItems.add(orangeWithQuantity);
        quantifiedItems.add(pearWithQuantity);

        return Items.fromCollection(quantifiedItems);
    }

    public static OfferStrategy createDefaultOfferStrategy() {
        return new OfferStrategy(1, "Default offer strategy") {
            @Override public Offers calculateOffers(Items items) {
                return null;
            }
        };
    }

    public static OfferStrategies createDefaultOfferStrategies() {
        return createOfferStrategiesWithQuantities(10, 5, 3);
    }

    public static OfferStrategies createOfferStrategiesWithQuantities(int... quantities) {
        List<OfferStrategy> offerStrategies = IntStream.range(0, quantities.length)
                .mapToObj(i -> {
                    String description = "OfferStrategy-" + i;
                    return new OfferStrategy(i, description) {
                        @Override public Offers calculateOffers(Items items) {
                            if (items.isEmpty()) return Offers.empty();

                            Offer offer = Offer.create(String.format("Offer made from [%s]", description), Price.ONE);
                            OfferWithQuantity offerWithQuantity = OfferWithQuantity.create(offer, quantities[i]);
                            return Offers.fromCollection(Collections.singletonList(offerWithQuantity));
                        }
                    };
                })
                .collect(Collectors.toList());

        return OfferStrategies.fromCollection(offerStrategies);
    }


    private static Offer createThreeApplesForOnePoundOffer() {
        return Offer.create("3 Apples for £1", Price.of(-50));
    }

    public static Offers createDefaultOffers() {
        return Offers.fromCollection(Arrays.asList(
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

    public static Offers createOffersWithPrices(long... amounts) {
        List<OfferWithQuantity> offers = IntStream.range(0, amounts.length)
                .mapToObj(i -> Offer.create("offer-" + i, Price.of(amounts[i])))
                .map(offer -> OfferWithQuantity.create(offer, 1))
                .collect(Collectors.toList());

        return Offers.fromCollection(offers);
    }

    public static Items createItemsWithPrices(long... amounts) {
        List<QuantifiedItem> items = IntStream.range(0, amounts.length)
                .mapToObj(i -> Item.builder()
                        .barcode(i)
                        .name("item-" + i)
                        .price(Price.of(amounts[i]))
                        .build()
                )
                .map(item -> QuantifiedItem.create(item, 1))
                .collect(Collectors.toList());

        return Items.fromCollection(items);
    }

    public static OfferStrategy createOfferStrategyWithId(long id) {
        return new OfferStrategy(id, "OfferStrategy-" + id) {
            @Override public Offers calculateOffers(Items items) {
                throw new UnsupportedOperationException("Not supported in testing.");
            }
        };
    }
}

