# Time Value

## Problem statement
Expiration time is an essential property of any option contract.
On the agreed time and date the contracts cease to trade, and any obligations or rights come due or expire.

Some options expire tonight, some other options expire next quarter.
Therefore, time period before the expiry can be anything from minutes to months.
To unify the calculations, it's convenient to always measure time before the expiry in years.

Given the amount of days, hours and minutes before the expiry, calculate the remaining time in years.
You can assume that a year has 365 days, a day has 24 hours and an hour has 60 minutes.

Your task is to implement method
```java
double calculateTime(int days, int hours, int minutes)
```

## Limitations

0 <= days <= 10000

0 <= hours < 24

0 <= minutes < 60

Your result should not differ from the answer for more than $10^{-8}$.


## Examples
### Example 1

```
days = 1
hours = 0
minutes = 0
```
```
time = 0.002739726027397
```

One day is 1/365 of a year.

### Example 2

```
days = 365
hours = 0
minutes = 1
```
```
time = 1.000001902587519
```