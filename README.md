[![Build Status](https://travis-ci.org/AkiKanellis/kata01-supermarket-pricing.svg?branch=develop)](https://travis-ci.org/AkiKanellis/kata01-supermarket-pricing)
# Kata01: Supermarket Pricing
A solution to [Kata01: Supermarket Pricing](http://codekata.com/kata/kata01-supermarket-pricing).

## Download
Download [the latest JAR][1].

## User stories
The Kata's description is fairly vague giving us the freedom to define the user stories as we see fit.
We assume that the supermarket has item stock that needs to be managed and that the *user* is the stock manager. Given that,
our user stories are like so:
 
 - As a stock manager user I can add a new item to the stock.
 - As a stock manager user I can increase the quantity of an item so that I can update it in the arrival of more stock.
 - As a stock manager user I can decrease the quantity of an item so that I can update it in the sale of stock.
 - As a stock manager user I can see all the items in the stock.
 - As a stock manager user I can add a new offer strategy to the stock so that the customers can have more discounts.
 - As a stock manager user I can remove an offer strategy from the stock so that I can limit the customers' discounts.
 - As a stock manager user I can see all the current offer strategies.
 - As a stock manager user I can see all the applicable offers to the stock.
 - As a stock manager user I can see the total value of the offers that are currently applicable.
 - As a stock manager user I can see the total value of the stock, before the offers.
 - As a stock manager user I can see the total value of the stock, after the offers.
 
## Design
DescriptionPlaceholder

## Modules

### Supermarket module
The backend of the supermarket can be found in the [supermarket module](supermarket).

### Scripts
If you want to build the whole project, run all the tests and the code analysis tools then run the file:

`scripts/build.sh`

## Libraries

### Production

#### [AutoValue](https://github.com/google/auto)
DescriptionPlaceholder

### Testing

#### [JUnit](http://junit.org/junit4/)
DescriptionPlaceholder

#### [Mockito](http://site.mockito.org/)
DescriptionPlaceholder

#### [AssertJ](http://joel-costigliola.github.io/assertj/)
DescriptionPlaceholder

#### [EqualsVerifier](http://jqno.nl/equalsverifier/)
DescriptionPlaceholder

### Code analysis

#### [CheckStyle](http://checkstyle.sourceforge.net/)
DescriptionPlaceholder

#### [FindBugs](http://findbugs.sourceforge.net/)
DescriptionPlaceholder

#### [PMD](https://pmd.github.io/)
DescriptionPlaceholder

#### [Travis CI](https://travis-ci.org/)
DescriptionPlaceholder

## REST API
DescriptionPlaceholder

## License
This application itself is released under **MIT** license, see [LICENSE](./LICENSE).

All 3rd party open sourced libs distributed with this application are still under their own license.

[1]: https://github.com/AkiKanellis/kata01-supermarket-pricing/releases/download/v1.0/supermarket-1.0.jar