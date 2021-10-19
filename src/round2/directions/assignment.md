# Opposite Directions
## Problem Overview

This is an optimization problem. Finding the best solution can be nearly impossible,
so you will work on an approximation algorithm.
The details of the result evaluation can be found in the ```Scoring``` section.

## Problem Statement

You are given a list of trading opportunities.
Choose a subset of the opportunities and order them in the most profitable way.
There are two dimensions: ```A``` and ```B```.
Each opportunity has four fields encoded as a list: ```[ changeA, profitA, changeB, profitB ]```.
The opportunities you select are processed in your order to compute the final profit.
This works as follows:

- The values ```A```, ```B```, ```profit``` are initially equal to ```0```.
- Opportunities are processed one by one, performing the following:
  - ```A += changeA```
  - ```B += changeB```
  - ```profit += A * profitA + B * profitB```

Your goal is to maximize the final value of ```profit```.

Your task is to implement the method
```java
List<Integer> chooseOpportunities(List<List<Integer>> opportunities)
```

It should return a list of 1-based indices in ```opportunities```.

## Limitations
```
1 <= opportunities.size() <= 1000
-10 <= opportunity.change <= 10
-400 <= opportunity.profit <= 400
```

You may take each opportunity at most once.

## Scoring

The score is calculated as ```max(0, profit) / 1_000_000_000```
after processing all the taken opportunities in selected order.

## Test structure and time limits

There are 10 tests. Your final score for this problem is the average of your scores on these 10 tests.

Each test contains exactly 1000 opportunities. These opportunities are generated randomly, with change and profit values chosen uniformly from the possible values.

Time limit is 5 seconds per test.

## Examples
### Example 1

```
opportunities = [ [ -3, 10, -10, -5 ], [ 1, 3, 3, 7 ] ]

result = [ 2, 1 ]
```

Taking the opportunity with index ```2``` results in ```A = 1```, ```B = 3```, ```profit = 24```.
The next opportunity then changes those values to ```A = -2```, ```B = -7```, ```profit = 39```.
The final profit is ```39```.

### Example 2
```
opportunities = [ [ 10, 10, 10, 10 ], [ -10, -10, -10, -10 ], [10, 10, 10, 10], [10, 10, 10, 10] ]

result = [ 1, 3, 4 ]
```

After processing the opportunities ```1```, ```3``` and ```4```, the final ```profit``` is ```1200```.
