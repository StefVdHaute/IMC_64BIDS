package round2.datacenter;

import java.util.*;

public class DataCenterConnections {// FLOYD-WARSHALL
    public int findShortestDistance(int n,
                                    List<List<Integer>> microwaveConnections,
                                    List<List<Integer>> fiberConnections) {
        int[][] distances = new int[n][n];
        boolean[][] isFiber = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
            distances[i][i] = 0;
        }

        int n1, n2;

        for (List<Integer> fc : fiberConnections) {
            n1 = fc.get(0) - 1;
            n2 = fc.get(1) - 1;

            isFiber[n1][n2] = true;
            distances[n1][n2] = 1;

            isFiber[n2][n1] = true;
            distances[n2][n1] = 1;
        }
        for (List<Integer> mw : microwaveConnections) {
            n1 = mw.get(0) - 1;
            n2 = mw.get(1) - 1;

            distances[n1][n2] = 1;
            distances[n2][n1] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int newDist = sumInf(distances[i][k], distances[k][j]);

                    if (distances[i][j] > newDist) {
                        distances[i][j] = newDist;
                    }
                }
            }
        }

        return distances[0][n - 1] == Integer.MAX_VALUE ? -1 : distances[0][n - 1];
    }

    private int sumInf(int x, int y) {
        int sum;
        if (x == Integer.MAX_VALUE || y == Integer.MAX_VALUE) {
            sum = Integer.MAX_VALUE;
        } else {
            sum = x + y;
        }

        return sum;
    }
}
