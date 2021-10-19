# Shortest Subsequence
## Problem Statement

We have a variety of products, and each product has a full name.
Using this full name is inconvenient, so we want to shorten each name without getting confused.

Given a list of full names, produce a shorter version for each of them.
The shorter versions should follow these rules:

- It is a subsequence of the original name. (```AD``` is a subsequence of ```ABCDE```.)
- It is not a subsequence of any other full name.
- It is as short as possible. If there are multiple shortest versions with the same length, return any of them.

Your task is to implement the method
```java
List<String> findShortVersions(List<String> names)
```

## Limitations

Let ```n``` be the size of the list ```names```, and ```len``` be the length of the longest name in that list.
Then the following constraints hold:

```
2 <= n <= 50
2 <= len <= 50
4 <= n * len <= 100
```

Each name consists only of uppercase English letters and digits.

No name contains another name as a subsequence.

## Examples
### Example 1

```
names = [ "JAN21PUT110", "JAN21PUT120", "JUN21PUT120", "JAN22PUT110", "JAN22PUT120" ]
```
```
result = [ "111", "A1P2", "UN", "2211", "222" ]
```

This is one of the possible answers. Another one could be ```[ "111", "A112", "UU", "2211", "222" ]```.

## Example 2
```
names = [ "APPLE", "AAPL", "PEPEPLE" ]
```
```
result = [ "AE", "AA", "EE" ]
```
