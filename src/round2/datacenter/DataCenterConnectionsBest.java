package round2.datacenter;

import java.util.*;

public class DataCenterConnectionsBest {// DIJKSTRA, No neighboursMap
    private final long max = 7500;
    private Set<Long> isFiber;

    // test 6 == -1
    // test 5 & 9 fail for unknown reasons
    // tests >= 10 fail because of out of [memory|time]

    // There is still a problem in this implementation test 5 & 9 fail but i think not because of size or impossible
    // See DataCenterConnections3 for a possibly faster but equal implementation with too high memory usage
    // See DataCenterConnections for a different implementation that is possibly faster but suffers from same memory problem
    public int findShortestDistance(int n,
                                    List<List<Integer>> microwaveConnections,
                                    List<List<Integer>> fiberConnections) {

        isFiber = new HashSet<>();
        for (List<Integer> fC : fiberConnections) {
            isFiber.add(fC.get(0) * 10_0001L + fC.get(1));
            isFiber.add(fC.get(1) * 10_0001L + fC.get(0));
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

        return isFiber.contains((long) n + prev.get(n) * 10_0001L);
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
