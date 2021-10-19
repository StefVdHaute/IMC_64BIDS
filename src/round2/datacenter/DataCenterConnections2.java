package round2.datacenter;

import java.util.*;

public class DataCenterConnections2 {// DIJKSTRA, neighboursMap (is seemingly slower than no map)
    private Map<Integer, Set<Integer>> neighbours;
    private Set<Long> isFiber;

    public int findShortestDistance(int n,
                                    List<List<Integer>> microwaveConnections,
                                    List<List<Integer>> fiberConnections) {
        neighbours = new HashMap<>();
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
        while (!Q.isEmpty() && i <= 5000) {
            i++;

            current = Q.poll();

            int alt = dist.get(current) + 1;
            for (int v : getNeighbours(current, microwaveConnections, fiberConnections, isFiber(current, prev))) {

                if (alt < dist.getOrDefault(v, Integer.MAX_VALUE)) {
                    if(v == n) {
                        return alt;
                    }

                    dist.put(v, alt);
                    prev.put(v, current);
                    Q.add(v);
                }
            }
        }

        return dist.getOrDefault(n, -1);
    }

    private boolean isFiber(int n, Map<Integer, Integer> prev) {
        if (n == 1) {
            return true;
        }

        return isFiber.contains((long)n + prev.get(n) * 10_0001L);
    }

    private Collection<Integer> getNeighbours(int n,
                                              List<List<Integer>> microwaveConnections,
                                              List<List<Integer>> fiberConnections,
                                              boolean prevFiber) {
        n = prevFiber ? n : -n;

        return neighbours.computeIfAbsent(n, x -> {
            x = Math.abs(x);

            Set<Integer> nb = new HashSet<>();
            if (prevFiber) {
                for (List<Integer> posNeighbour : microwaveConnections) {
                    if (posNeighbour.get(0).equals(x)) {
                        nb.add(posNeighbour.get(1));
                    } else if (Objects.equals(posNeighbour.get(1), x)) {
                        nb.add(posNeighbour.get(0));
                    }
                }
            }

            for (List<Integer> posNeighbour : fiberConnections) {
                if (Objects.equals(posNeighbour.get(0), x)) {
                    nb.add(posNeighbour.get(1));
                } else if (Objects.equals(posNeighbour.get(1), x)) {
                    nb.add(posNeighbour.get(0));
                }
            }

            return nb;
        });
    }
}
