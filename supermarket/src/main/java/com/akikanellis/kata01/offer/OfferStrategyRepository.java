package com.akikanellis.kata01.offer;

/**
 * A repository holding {@link com.akikanellis.kata01.offer.OfferStrategy} elements.
 */
public interface OfferStrategyRepository {

    /**
     * Returns if the repository contains a specified offer strategy.
     *
     * @param offerStrategy the strategy to check
     * @return true if the repository contains the specified offer strategy, false if not
     */
    boolean contains(OfferStrategy offerStrategy);

    /**
     * Adds the specified offer strategy to the repository.
     *
     * @param offerStrategy the strategy to add
     */
    void add(OfferStrategy offerStrategy);

    /**
     * Removes the specified offer strategy from the repository.
     *
     * @param offerStrategy the strategy to remove
     */
    void remove(OfferStrategy offerStrategy);

    /**
     * Returns the offer strategy with the given id, or throws an exception.
     *
     * @param id the id of the strategy
     * @return the offer strategy if found
     * @throws OfferStrategyNotFoundException if there is no such offer strategy for the given id
     */
    OfferStrategy getById(long id);

    /**
     * Returns all the offer strategies currently contained.
     *
     * @return all the offer strategies in the repository. If there are no offer strategies then this returns an empty
     * representation
     */
    OfferStrategies getAll();
}
