package round1.groups;

import java.util.*;
import java.util.stream.Collectors;

public class PointGroups {

    // secondPoints can not contain duplicate points
    // this is an acceptable limitation since secondPoints is a part of a graph thus duplicates shouldn't exist
    public List<Integer> findGroups(List<Integer> firstPoints, List<Integer> secondPoints) {
        Map<Integer, Map<String, Integer>> transformations = new HashMap<>();// Contains a map, of all transformations, for the index of a given point
        Map<String, Integer> transformationCounter = new HashMap<>(); // counts how many identical transformations there are for different points

        for (int i = 0; i < firstPoints.size(); i += 2) {
            Map<String, Integer> posTransformations = new HashMap<>();
            transformations.put(i, posTransformations);

            for (int j = 0; j < secondPoints.size(); j += 2) {
                int x1 = firstPoints.get(i);
                int y1 = firstPoints.get(i + 1);
                int x2 = secondPoints.get(j);
                int y2 = secondPoints.get(j + 1);

                int difX = x2 - x1;
                int difY = y2 - y1;

                // The simulated transformations only find transformations where only a rotation, scaling or translation has happened
                // This is something but not what is wanted, a scaling after a rotation should also be detected
                // Translation
                String translation = difX + "," + difY;
                posTransformations.put(translation, j);
                transformationCounter.compute(translation, (k, v) -> (v == null) ? 1 : v + 1);

                // Scaling
                if (difX == difY) {
                    String scaling = "*" + difX;
                    posTransformations.put(scaling, j);
                    transformationCounter.compute(scaling, (k, v) -> (v == null) ? 1 : v + 1);
                }

                // Rotation
                double n1 = Math.sqrt(x1 * x1 + y1 * y1);
                double n2 = Math.sqrt(x2 * x2 + y2 * y2);
                double angle = Math.acos((x1 * x2 + y1 * y2) / (n1 * n2)) * 180 / Math.PI;

                // we only care about integers since the given points can't be doubles as defined by the given function interface
                if (x2 == Math.round(x1 * Math.cos(Math.toRadians(angle)) - y1 * Math.sin(Math.toRadians(angle)))
                        && y2 == Math.round(x1 * Math.sin(Math.toRadians(angle) + y1 * Math.cos(Math.toRadians(angle))))) {
                    String rotation = "rot" + angle;
                    posTransformations.put(rotation, j);
                    transformationCounter.compute(rotation, (k, v) -> (v == null) ? 1 : v + 1);
                }
            }
        }

        // An ordered list, the first element is the most promising transformation for getting a large group
        List<String> transformationOrder = transformationCounter.keySet().stream()
                .sorted(Comparator.comparingInt(transformationCounter::get).reversed())
                .collect(Collectors.toList()); //toList() instead of collect() only works in java 16

        Set<Integer> takenPointsForTransformations = new HashSet<>(); // set of indexes of all secondPoints which have been given a transformations
        List<Integer> group = new ArrayList<>(Collections.nCopies(firstPoints.size(), -1));

        int j = 0;
        for (int i = 0; i < transformations.size(); i++) {
            Map<String, Integer> posTransformations = transformations.get(j);

            int index = posTransformations.getOrDefault(transformationOrder.get(0), -1);
            int k = 0;

            // while (! (index >= 0 && ! takenPointsForTransformations.contains(index)) {
            while (takenPointsForTransformations.contains(index) || index < 0) {
                k++;

                index = posTransformations.getOrDefault(transformationOrder.get(k), -1);
            }

            group.set(i, k + 1);
            group.set(transformations.size() + index / 2, k + 1);
            takenPointsForTransformations.add(index);

            j += 2;
        }

        // rescaling of values in group to show a semblance of order
        Map<Integer, Integer> rescaleMap = new HashMap<>();
        /* java 16 solution for next for-loop
        AtomicInteger groupIndex = new AtomicInteger();
        for (int i = 0; i < group.size(); i++) {
            group.set(i, rescaleMap.computeIfAbsent(group.get(i), key -> groupIndex.incrementAndGet()));
        }
         */
        final int[] groupIndex = {0};

        for (int i = 0; i < group.size(); i++) {
            group.set(i,
                    rescaleMap.computeIfAbsent(group.get(i), key -> {
                        groupIndex[0]++;
                        return groupIndex[0];
                    })
            );
        }

        return group;
    }
}
