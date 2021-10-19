package round2.datacenter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataCenterConnectionsParallel {// DIJKSTRA, No neighboursMap, parrallel (with streams)
    private final long max = 7500;
    private Set<Long> isFiber;

    public int findShortestDistance(int n,
                                    List<List<Integer>> microwaveConnections,
                                    List<List<Integer>> fiberConnections) {
        isFiber = fiberConnections.parallelStream()
                .flatMap(x -> Stream.of(
                        x.get(0) * 10_0001L + x.get(1),
                        x.get(1) * 10_0001L + x.get(0)
                ))
                .collect(Collectors.toSet());

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
        Stream<List<Integer>> stream;
        if (prevFiber) {
            stream = Stream.concat(microwaveConnections.parallelStream(), fiberConnections.parallelStream());
        } else {
            stream = fiberConnections.parallelStream();
        }

        Set<Integer> neighbours = stream.map(x -> {
                    if (x.get(0) == n) {
                        return x.get(1);
                    } else if (x.get(1) == n) {
                        return x.get(0);
                    }
                    return 0;
                })
                .collect(Collectors.toSet());

        neighbours.remove(0);
        return neighbours;
    }
}
