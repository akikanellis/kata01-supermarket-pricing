# ⚠️ This project has been deprecated ⚠️

This project contains some very old code that does not reflect my standards anymore. I will be leaving
the project in an archived state in case it is still useful to someone.

[![Build Status](https://travis-ci.org/AkiKanellis/kata01-supermarket-pricing.svg?branch=develop)](https://travis-ci.org/AkiKanellis/kata01-supermarket-pricing)
# Kata01: Supermarket Pricing
A solution to [Kata01: Supermarket Pricing](http://codekata.com/kata/kata01-supermarket-pricing).

## Download
Download [the latest JAR][1].

## User stories
The Kata's description is fairly vague giving us the freedom to define the user stories as we see fit. We assume that
the supermarket has a stock of items that needs to be managed and that the *user* is the Stock Manager. Given that, our
user stories are like so:
 
 1. As a Stock Manager user, I can add a new item to the stock.
 - As a Stock Manager user, I can increase the quantity of an item so that I can update it in the arrival of more stock.
 - As a Stock Manager user, I can decrease the quantity of an item so that I can update it in the sale of stock.
 - As a Stock Manager user, I can see all the items in the stock.
 - As a Stock Manager user, I can add a new offer strategy for the stock so that the customers can have more discounts.
 - As a Stock Manager user, I can remove an offer strategy from the stock so that I can limit the customers' discounts.
 - As a Stock Manager user, I can see all the current offer strategies.
 - As a Stock Manager user, I can see all the applicable offers to the stock.
 - As a Stock Manager user, I can see the total value of the offers that are currently applicable.
 - As a Stock Manager user, I can see the total value of the stock, before the offers.
 - As a Stock Manager user, I can see the total value of the stock, after the offers.
 
## Design

### Architecture
The design of the supermarket follows a 3-layer [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html), the layers are:

- **Entities**, which includes our business POJOs and the repository contracts (interfaces).
- **Use Case**, with the main business rules of our application. Each Use Case maps to a user story.
- **Frameworks & Drivers**, where the concrete implementation of our repositories reside.

The repositories are implemented as in-memory repositories. If we were to implement them using persistence (like a
database) and we were to implement a UI (like a console application) then we could use two additional layers:

- **Interface adapters**, for converting the database rows to business entities and the business entities to View Models.
- **Presentation**, where the View Models are presented to the view. Preferably by leveraging a Design Pattern such as 
Model-View-Presenter (MVP).

### Packaging
A [Package-By-Feature](http://www.javapractices.com/topic/TopicAction.do?Id=205) type of packaging is used.

### Branching model
The branching model used is [GitFlow](http://nvie.com/posts/a-successful-git-branching-model/).

### Notes
This project was made to be simple, readable and maintainable. To achieve that certain decisions have been made:

- There is no Dependency Injection framework used. If we were to create a production ready supermarket, then we would
  use a Dependency Injection framework such as [Dagger 2](https://google.github.io/dagger/). By taking advantage of such a framework we could reduce
  the visibility of certain internal classes (like the repository implementations) and we could also easily establish whichever
  resources we want as singletons.
- The supermarket does not support asynchronous operations, something that a real one should. This could be achieved
  by using a Reactive Extensions framework such as [RxJava](https://github.com/ReactiveX/RxJava) to manage multiple asynchronous streams. Additionally, we
  would have to make sure our reads and writes to the repositories are synchronized.
- All repositories are in-memory, meaning that no state is persisted. We would have to change that by making database
  implementations of the repositories.

## Modules

### Supermarket module
The supermarket can be found in the [supermarket module](supermarket).

### Scripts
If you want to build the whole project, run all the tests and the code analysis tools then run the file:

`scripts/build.sh`

## Libraries

### Production

#### [AutoValue](https://github.com/google/auto)
AutoValue provides us with an easy way to write immutable POJOs with generated `equals`, `hashcode` and `toString`
methods. It also provides a painless way to implement the Builder Pattern. These advantages allow us to have the
boilerplate code generated for us which lets us focus on the features we want to provide to the user.

### Testing

#### [JUnit](http://junit.org/junit4/)
The de facto framework when it comes to testing in Java. We use JUnit for both Unit and Integration testing.

#### [Mockito](http://site.mockito.org/)
This gives us a versatile and easy to use API for creating mocks, stubs and fakes for our tests. It makes the tests
very readable and easy to change.

#### [AssertJ](http://joel-costigliola.github.io/assertj/)
Provides a fluent API for assertions with better error messages than the default ones. The tests are more readable
and easy to understand.

#### [EqualsVerifier](http://jqno.nl/equalsverifier/)
An extensive tester for verifying whether the `equals` and `hashcode` contracts in a class are met.

### Code analysis

#### [CheckStyle](http://checkstyle.sourceforge.net/)
A tool for making sure our Java code adheres to the coding standard that has been defined. The coding standard used is
the Oracle standard along with some modifications.

#### [FindBugs](http://findbugs.sourceforge.net/)
A static analysis tool for finding bugs in Java code by analysing the generated bytecode.

#### [PMD](https://pmd.github.io/)
Same as FindBugs but works on the source code.

#### [Travis CI](https://travis-ci.org/)
Our Continuous Integration server for making sure errors are detected before they are merged.

## REST API
Instead of only having the backend exposed internally, we could expose it through a REST API.
  
Assuming that our supermarket's website is *supermarket.com* and we are in version v1.0, then the REST API could look
like this:

### Root
`api.supermarket.com`

### Stock
`api.supermarket.com/1.0/stock`

### Main Resources

- `/items`
- `/offerstrategies`
- `/applicableoffers`

### Supported verbs

#### For `/items`

| Verb                                      | Description                   |
|-------------------------------------------|-------------------------------|
| **GET** `/items`                          | Get all items                 |
| **POST** `/items`                         | Create new item               |
| **PUT** `/items/<id>/increase/<quantity>` | Increase item quantity        |
| **PUT** `/items/<id>/decrease/<quantity>` | Decrease item quantity        |
| **GET** `/items/valuebeforeoffers`        | Get stock value before offers |
| **GET** `/items/valueafteroffers`         | Get stock value after offers  |

#### For `/offerstrategies`

| Verb                                  | Description               |
|---------------------------------------|---------------------------|
| **GET** `/offerstrategies`            | Get all offer strategies  |
| **POST** `/offerstrategies`           | Create new offer strategy |
| **DELETE** `/offerstrategies/<id>`    | Delete offer strategy     |

#### For `/applicableoffers`

| Verb                              | Description                   |
|-----------------------------------|-------------------------------|
| **GET** `/applicableoffers`       | Get all applicable offers     |
| **GET** `/applicableoffers/value` | Get applicable offers value   |

## License
This application itself is released under **MIT** license, see [LICENSE](./LICENSE).

All 3rd party open sourced libs distributed with this application are still under their own license.

[1]: https://github.com/AkiKanellis/kata01-supermarket-pricing/releases/download/v1.0/supermarket-1.0.jar
