# The Retail Store Discounts

## Description

On a retail website, the following
discounts apply:
1. If the user has gold card of the store,
   he gets a 30% discount
2. If the user has silver card of the store,
   he gets a 20% discount
3. If the user is an affiliate of the store,
   he gets a 10% discount
4. If the user has been a customer for
   over 2 years, he gets a 5% discount.

5. For every $200 on the bill, there
   would be a $ 5 discount (e.g. for $ 950,
   you get $ 20
   as a discount).
6. The percentage based discounts do
   not apply on phones.
7. A user can get only one of the
   percentage based discounts on a bill.
   Write a program in java such that given a
   bill, it finds the net payable amount.
   User interface is not required.


What we care about:

Required Activities

• Object oriented programming
   approach, provide a high level UML class
   diagram of
   all the key classes in your solution. This
   can either be on paper or using a CASE
   tool

• Code to be generic and simple

• Leverage today&#39;s best coding practices

• Clear README.md that explains how
    the code and the test can be run and
    how the
    coverage reports can be generated


Bonus Activities

• Create scripts, e.g. Maven, etc. to:

o build the project from the command-
line

o run static code analysis such as linting

o run unit tests and code coverage

• SonarQube report for the code showing
its quality summary

Create a GitHub repository, ensure the
name is generic and doesn’t have any
company
names. Commit your code to the GitHub
repository and share the link with us.

## Installing

After checking out the git repo run the following maven command

```bash
mvn clean install
```

This should install all packages, run all unit tests

## Starting

To start the server please run the following maven command

```bash
mvn spring-boot:run
```


## Testing

To execute the unit tests against the business logic service classes please run the following maven command

```bash
mvn test
```

## Code coverage

The code coverage report:


| Package                  | Coverage |
|--------------------------|----------|
| all classes              | 85.00%   |
| src/main/java/controller | 100.00%  |
| src/main/java/helper     | 72.00%   | 
| src/main/java/models     | 90.00%   | 
| src/main/java/services   | 100.00%  | 


## Using

### API Endpoint

* Http Method - **POST**
* Endpoint - **localhost:8080/api/v1/discounts**

Example request

```json
{
   "user": {
      "type": "CUSTOMER",
      "registerDate": "2019-03-22"
   },
   "bill": {
      "items": [
         {
            "type": "GROCERY",
            "price": 4.2
         },
         {
            "type": "PHONES",
            "price": 440.42
         },
         {
            "type": "GROCERY",
            "price": 2.1
         },
         {
            "type": "OTHER",
            "price" : 50.12
         },
         {
            "type": "CLOTHES",
            "price" : 150.25
         }
      ]
   }
}

```

The response is net payable amount.

```json
626.92325
```
