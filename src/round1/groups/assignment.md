# Point Groups
## Problem Overview

This is an optimization problem. Finding the best solution can be nearly impossible, so you will work on an approximation algorithm. The result evaluation details can be found in the Scoring section.

## Problem Statement

Finding various patterns can be engaging and challenging, no matter in which area they appear. In this task we're interested in two-dimensional points that form repeating patterns.

You are given two sets of points. Each set contains N points with integer coordinates.

Divide all the points into multiple groups in the following way:

- Each group should contain points from both sets.
- Each group should have the same number of points from the first and the second set.
- Within each group, the first set's points should exactly map into the second set's points after performing some of these operations:
  - Translation
  - Rotation for an arbitrary angle
  - Uniform scaling (scaling with both coordinates having the same multiplier)
- The operations are applied to each subgroup independently.
- The operations can be applied in any order any amount of times.

The goal is to have as few groups as possible, while keeping the possibility of subgroup mapping between the two sets. You don't need to provide the operations, but a valid mapping should exist.

Your task is to implement method

```java
List<Integer> findGroups(List<Integer> firstPointSet, List<Integer> secondPointSet)
```

Return a list that contains a group number for each input point. The list size should be 2*N, with the first set's groups followed by the second set's groups.

Input list format: X_1, Y_1, X_2, Y_2, ... , X_N, Y_N

Output list format: G_1, G_2, ... , G_N, H_1, H_2, ... , H_N

## Limitations

1 <= X_i, Y_i <= 100

1 <= N <= 1000

1 <= G_i, H_i <= N

## Scoring

When 2*N input points are divided into K groups, the score is ```(N-K+1)/N```.

## Test structure and time limits

There are 10 tests. The final score is an average between these 10 tests.

Time limit is 5 seconds per test.

Each test contains exactly 1000 points per set.

All the points are generated randomly: they have random (uniform) coordinates.

## Examples
### Example 1

```
firstPointSet = [ 1,1, 2,2, 5,5 ]
secondPointSet = [ 5,5, 1,1, 3,3 ]

groups = [ 2,1,2, 2,2,1 ]
```

The input represents 2 sets of points: ```(1,1) (2,2) (5,5)``` and ```(5,5) (1,1) (3,3)```.

Let's split the points into 2 groups: ```[ (2,2) | (3,3) ]``` and ```[ (1,1) (5,5) | (5,5) (1,1) ]```.

There are 3 points per set and 2 groups, so the score is ```0.66(6)```.

### Example 2

```
firstPointSet = [ 1,1, 1,3, 2,1 ]
secondPointSet = [ 6,1, 6,3, 7,1 ]

groups = [ 3,3,3, 3,3,3 ]
```

To exactly map the points between sets, you can translate the points of one of the subgroups.

Groups numbers don't have to be consecutive, but they should stay within the 1..N range.

There are 3 points per set and 1 group, so the score is ```1.0```.

### Example 3

```
firstPointSet = [ 1,1, 1,3, 2,1 ]
secondPointSet = [ 1,1, 5,1, 5,3 ]

groups = [ 1,1,1, 1,1,1 ]
```

You can rotate the points of a subgroup for an arbitrary angle and scale them uniformly.

### Example 4

```
firstPointSet = [ 1,1, 1,3, 2,1 ]
secondPointSet = [ 1,1, 2,3, 2,1 ]

groups = [ 1,1,2, 1,1,2 ]
```

Mirroring is not allowed, so we can't put all the points into one group.

### Example 5

```
firstPointSet = [ 1,1, 1,3, 2,1 ]
secondPointSet = [ 1,1, 1,6, 2,1 ]

groups = [ 1,2,1, 1,2,1 ]
```

The scaling should be uniform, so we can't put all the points into one group.

### Example 6

```
firstPointSet = [ 16,55, 4,15, 3,22, 4,11, 7,11, 2,28, 71,14, 7,15 ]
secondPointSet = [ 45,50, 13,37, 50,50, 75,50, 59,38, 17,28, 3,22, 66,62 ]

groups = [ 1,2,3,2,2,1,3,2, 3,1,2,2,2,3,1,2 ]
```

There are 8 points per set and 3 groups, so the score is ```0.75```.
