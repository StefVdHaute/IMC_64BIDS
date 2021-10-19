# Flat calls and puts
## Problem Statement

When you own a financial instrument,
you have a position in it that involves a lot of risks.

At the moment you hold a bunch of options.
To limit the risks, you've decided to execute your options with the total volume
of executed puts being equal to the total volume of executed calls.

Given strike prices and volumes of put and call options that you hold,
compute the maximal profit you can make if you only execute desired options.
Keep in mind that your position after all the operations should be 0.

Your task is to implement method

```java
int calculateProfit(List<Integer> callStrikePrices, List<Integer> callVolumes, List<Integer> putStrikePrices, List<Integer> putVolumes)
```

## Definitions

Call option gives the right, but not the obligation, to buy an agreed quantity (volume) of a particular financial instrument from the seller of the option for a certain price (the strike price).

Put option gives the right, but not the obligation, to sell an agreed quantity (volume) of a particular financial instrument to the seller of the option for a certain price (the strike price).

## Limitations

1 <= callStrikePrices.size() == callVolumes.size() <= 10

1 <= putStrikePrices.size() == putVolumes.size() <= 10

Each price is an integer value between 1 and 1000 inclusive.

Each volume is an integer value between 1 and 1000 inclusive.

## Examples
### Example 1

```
callStrikes = [ 3, 9, 7 ]
callVolumes = [ 1, 3, 2 ]
putStrikes = [ 10, 10 ]
putVolumes = [ 2, 2 ]
```
```
profit = 10
```
It's profitable to execute the 3-call, the 9-call, and both 10-puts. You pay 30 for calls and you get 40 from puts, so the profit is 10. The put and call volumes are equal, so your position is flat.

### Example 2

```
callStrikes = [ 1, 1, 1, 1 ]
callVolumes = [ 1, 10, 100, 1000 ]
putStrikes = [ 1000, 1000, 1000 ]
putVolumes = [ 9, 99, 999 ]
```
```
profit = 0
```
The options are extremely profitable, but the volumes simply don't match.

### Example 3

```
callStrikes = [ 4, 10 ]
callVolumes = [ 6, 4 ]
putStrikes = [ 8, 5 ]
putVolumes = [ 5, 5 ]
```
```
profit = 1
```
Not every trade is clearly profitable, but the result is still positive.

### Example 4

```
callStrikes = [ 549, 170, 748, 1, 341, 270, 148, 672, 882, 427 ]
callVolumes = [ 213, 313, 666, 896, 617, 430, 686, 135, 165, 148 ]
putStrikes = [ 388, 282, 539, 769, 6, 860, 733, 964, 825, 422 ]
putVolumes = [ 352, 476, 287, 22, 288, 327, 942, 846, 564, 324 ]
```
```
profit = 1854183
```