package round2.datacenter;

import java.util.*;

public class DataCenterConnections3 {// DIJKSTRA, No neighboursMap
    private final long max = 7500;
    private boolean[][] isFiber;

    public int findShortestDistance(int n,
                                    List<List<Integer>> microwaveConnections,
                                    List<List<Integer>> fiberConnections) {
        isFiber = new boolean[n][];
        for (int i = 0; i < n; i++) {
            isFiber[i] = new boolean[i + 1];
        }
        for (List<Integer> fc : fiberConnections) {
            int n1 = fc.get(0) - 1;
            int n2 = fc.get(1) - 1;

            if (n1 >= n2) {
                isFiber[n1][n2] = true;
            } else {
                isFiber[n2][n1] = true;
            }
        }

        Map<Integer, Integer> dist = new HashMap<>();// Default = INF
        Map<Integer, Integer> prev = new HashMap<>();// Default = null

        Queue<Integer> Q = new PriorityQueue<>(Comparator.comparingInt(node ->
                dist.getOrDefault(node, Integer.MAX_VALUE)));

        int current = 1;
        dist.put(current, 0);
        Q.add(current);

        int i = 0;
        while (!Q.isEmpty() && i <= max) {
            current = Q.poll();

            int newDist = dist.get(current) + 1;
            for (int v : getNeighbours(current, microwaveConnections, fiberConnections, isFiber(current, prev))) {
                if (newDist < dist.getOrDefault(v, Integer.MAX_VALUE)) {
                    if (v == n) {
                        return newDist;
                    }

                    dist.put(v, newDist);
                    prev.put(v, current);
                    Q.add(v);
                }
            }
            i++;
        }

        return dist.getOrDefault(n, -1);
    }

    private boolean isFiber(int n, Map<Integer, Integer> prev) {
        if (n == 1) {
            return true;
        }

        int n1 = n - 1;
        int n2 = prev.get(n) - 1;
        if (n1 >= n2) {
            return isFiber[n1][n2];
        } else {
            return isFiber[n2][n1];
        }
    }

    private Collection<Integer> getNeighbours(int n,
                                              List<List<Integer>> microwaveConnections,
                                              List<List<Integer>> fiberConnections,
                                              boolean prevFiber) {

        Set<Integer> neighbours = new HashSet<>();
        if (prevFiber) {
            for (List<Integer> posNeighbour : microwaveConnections) {
                if (posNeighbour.get(0) == n) {
                    neighbours.add(posNeighbour.get(1));
                } else if (posNeighbour.get(1) == n) {
                    neighbours.add(posNeighbour.get(0));
                }
            }
        }

        for (List<Integer> posNeighbour : fiberConnections) {
            if (posNeighbour.get(0) == n) {
                neighbours.add(posNeighbour.get(1));
            } else if (posNeighbour.get(1) == n) {
                neighbours.add(posNeighbour.get(0));
            }
        }

        return neighbours;
    }
}
