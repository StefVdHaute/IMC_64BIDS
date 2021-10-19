# Option Combinations
## Problem Statement

In options trading, a combination is a trade constructed from multiple option contracts on the same underlying asset.
Traders and investors use combinations for a wide variety of trading strategies
because they can provide specific risk-reward payoffs.

Combinations are always composed of more than one option contract.
Each option contract is characterized by its expiration date, type (put or call) and strike price.
For example, we can represent option contracts like ```18Feb2022 Put 1000``` or ```19Nov2021 Call 1700```.

Given the numbers of available strike prices and expiration dates,
count the number of combinations that can be created from the available option contracts.

Because the number of combinations can be quite large, it should be represented as a String.

Your task is to implement the method

```java
String countCombinations(int strikes, int expiries)
```

## Limitations
```
1 <= strikes <= 1000
1 <= expiries <= 50
```

## Examples
### Example 1
```
strikes = 1
expiries = 1
```
```
result = "1"
```

There are two available options: one put and one call with the same expiry and strike price. Using them together is the only way to create a combination.

### Example 2
```
strikes = 2
expiries = 1
```
```
result = "11"
```

There are four available options: two puts and two calls with the same expiry but different strike prices. The options {A, B, C, D} can be combined in 11 different ways: {A, B, C, D}, {A, B, C}, {A, B, D}, {A, C, D}, {B, C, D}, {A, B}, {A, C}, {A, D}, {B, C}, {B, D}, {C, D}.

### Example 3
```
strikes = 3
expiries = 2

result = "4083"
```

There are 12 available option contracts, which can be combined in 4083 different ways.
