# Data Center Traversal
## Problem Statement

We want to send a message quickly from one data center to another.
Unless they are directly connected, the message will need to traverse a network of data centers.

Data centers may be connected in two ways: by fiber cable and by microwave transmission.
We know which kinds of connections are available between each pair of data centers.
A package can travel from one data center to another using these connections.
There is a technical constraint: a message cannot be sent by two microwave transmissions in a row.
If it is sent by microwave transmission, it must first travel along a fiber cable
before it can be transmitted over a microwave connection again.

There are ```n``` data centers, identified by the numbers ```1``` to ```n```.
We want to send a message from data center ```1``` to data center ```n```. Find the shortest path
(the least number of hops required to get a message from data center ```1``` to data center ```n```)
that satisfies the constraint.

If it is not possible to reach the data center ```n``` from data center ```1``` while satisfying
the constraint at all, then return -1.

Your task is to implement the method

```java
int findShortestDistance(int n,
                List<List<Integer>> microwaveConnections,
                List<List<Integer>> fiberConnections)
```

The microwave and fiber connections are given as lists of lists of integers.
The inner lists are all of size 2, and of the form ```[i,j]```
which signifies that there is a connection between data centers ```i``` and ```j```.

The connections are bi-directional, and you can use them multiple times in both directions.
Visiting a data center multiple times on the path to the target is allowed.
It is possible to have both kinds of connections between a pair of data centers, but at most one of each kind.

## Limitations

```2 <= n <= 10^5```

```0 <= microwaveConnections.size() <= 10^5```

```0 <= fiberConnections.size() <= 10^5```

## Examples
### Example 1
```
n = 5
microwaveConnections = [[1, 4], [4, 5]]
fiberConnections = [[1, 2], [3, 4], [4, 5]]
```
```
result = 2
```

We can connect as follows: ```1 -microwave-> 4 -fiber-> 5```

### Example 2
```
n = 5
microwaveConnections = [[1, 4], [4, 5]]
fiberConnections = [[2, 3], [2, 5], [3, 4]]
```
```
result = 4
```

We can connect as follows: ```1 -microwave-> 4 -fiber-> 3 -fiber-> 2 -fiber-> 5```

Another possible optimal path is ```1 -microwave-> 4 -fiber-> 3 -fiber-> 4 -microwave-> 5```
