package round1.options;

import java.util.*;

public class FlatCallsAndPuts {
    public int calculateProfit(List<Integer> callStrikePrices, List<Integer> callVolumes, List<Integer> putStrikePrices, List<Integer> putVolumes) {
        int callVolCombSize = (int) Math.pow(2, callVolumes.size()) - 1;
        int putVolCombSize = (int) Math.pow(2, putVolumes.size()) - 1;
        int max = Math.max(callVolCombSize, putVolCombSize);
        int i, j;

        Map<Integer, Integer> callVolToMinPriceMap = new HashMap<>();
        Map<Integer, Integer> putVolToMaxPriceMap = new HashMap<>();

        int callVolSum;
        int putVolSum;
        int callVolPrice;
        int putVolPrice;
        BitSet bitmask;

        // Find sums of callVolumes equal to the same sum of putVolumes
        for (i = 0; i < max; i++) {
            callVolSum = putVolSum = callVolPrice = putVolPrice = 0;
            bitmask = BitSet.valueOf(new long[]{i + 1});

            j = bitmask.nextSetBit(0);

            while (j >= 0) {
                if (i < callVolCombSize && j < callVolumes.size()) {
                    callVolSum += callVolumes.get(j);
                    callVolPrice += callVolumes.get(j) * callStrikePrices.get(j);
                }
                if (i < putVolCombSize && j < putVolumes.size()) {
                    putVolSum += putVolumes.get(j);
                    putVolPrice += putVolumes.get(j) * putStrikePrices.get(j);
                }
                j = bitmask.nextSetBit(j + 1);
            }

            int finalCallVolPrice = callVolPrice;
            callVolToMinPriceMap.compute(callVolSum, (k, value) ->
                    (value == null) // value not yet set
                            ? finalCallVolPrice
                            : Math.min(finalCallVolPrice, value)
            );
            int finalPutVolPrice = putVolPrice;
            putVolToMaxPriceMap.compute(putVolSum, (k, value) ->
                    (value == null) // value not yet set
                            ? finalPutVolPrice
                            : Math.max(finalPutVolPrice, value)
            );

        }

        // Find the most profitable couple of sums
        max = 0;

        for (int volume : callVolToMinPriceMap.keySet()) {
            if (putVolToMaxPriceMap.get(volume) != null) {
                max = Math.max(max, putVolToMaxPriceMap.get(volume) - callVolToMinPriceMap.get(volume));
            }
        }

        return max;
    }
}
