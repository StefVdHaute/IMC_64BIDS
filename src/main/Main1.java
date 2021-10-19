package main;

import round1.expiry.TimeValue;
import round1.fastread.FastRead;
import round1.groups.PointGroups;
import round1.options.FlatCallsAndPuts;

import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        TimeValue val = new TimeValue();

        System.out.println(val.calculateTime(1, 0, 0));
        System.out.println("----------------------------------------------------------------------");
        System.out.println(val.calculateTime(365, 0, 1));
        System.out.println("----------------------------------------------------------------------");

        FastRead fr = new FastRead();

        String marketState =
                "{\"timestamp\":0,\"book\":\"AAPL\",\"bids\":[],\"asks\":[{\"price\":600,\"volume\":10}]}";

        // S13 R2 S8 R5 S9 R1 S9 R1 S8 R4
        System.out.println(fr.fastRead(700, 900, marketState));
        System.out.println("S13 R2 S8 R5 S9 R1 S9 R1 S8 R4");
        System.out.println("----------------------------------------------------------------------");

        marketState =
                "{\"timestamp\":15,\"book\":\"TSLA\",\"bids\":[{\"price\":800,\"volume\":50},{\"price\":850,\"volume\":110}],\"asks\":[]}";

        // S13 R3 S8 R5 S9 R1 S8 R4 S9 R4 S9 R4 S9 R5 S9 R1
        System.out.println(fr.fastRead(500, 900, marketState));
        System.out.println("S13 R3 S8 R5 S9 R1 S8 R4 S9 R4 S9 R4 S9 R5 S9 R1");
        System.out.println("----------------------------------------------------------------------");

        FlatCallsAndPuts FCP = new FlatCallsAndPuts();

        List<Integer> callStrikes = List.of(3, 9, 7);
        List<Integer> callVolumes = List.of(1, 3, 2);
        List<Integer> putStrikes = List.of(10, 10);
        List<Integer> putVolumes = List.of(2, 2);

        System.out.println(FCP.calculateProfit(callStrikes, callVolumes, putStrikes, putVolumes));
        System.out.println("----------------------------------------------------------------------");

        PointGroups PG = new PointGroups();

        // 1
        List<Integer> firstPointSet = List.of(1, 1, 2, 2, 5, 5);
        List<Integer> secondPointSet = List.of(5, 5, 1, 1, 3, 3);

        testPointGroups(PG, firstPointSet, secondPointSet);

        // 2
        firstPointSet = List.of(1, 1, 1, 3, 2, 1);
        secondPointSet = List.of(6, 1, 6, 3, 7, 1);

        testPointGroups(PG, firstPointSet, secondPointSet);

        // 3
        firstPointSet = List.of(1, 1, 1, 3, 2, 1);
        secondPointSet = List.of(1, 1, 5, 1, 5, 3);

        testPointGroups(PG, firstPointSet, secondPointSet);

        // 3*, rotation-test
        firstPointSet = List.of(1, 1, 1, 3, 2, 1);
        secondPointSet = List.of(-1, 1, -3, 1, -1, 2);

        testPointGroups(PG, firstPointSet, secondPointSet);

        // 4
        firstPointSet = List.of(1, 1, 1, 3, 2, 1);
        secondPointSet = List.of(1, 1, 3, 3, 2, 1);

        testPointGroups(PG, firstPointSet, secondPointSet);

        // 5
        firstPointSet = List.of(1, 1, 1, 3, 2, 1);
        secondPointSet = List.of(1, 1, 1, 6, 2, 1);

        testPointGroups(PG, firstPointSet, secondPointSet);

        // 6
        firstPointSet = List.of(16, 55, 4, 15, 3, 22, 4, 11, 7, 11, 2, 28, 71, 14, 7, 15);
        secondPointSet = List.of(45, 50, 13, 37, 50, 50, 75, 50, 59, 38, 17, 28, 3, 22, 66, 62);

        testPointGroups(PG, firstPointSet, secondPointSet);
        System.out.println("----------------------------------------------------------------------");
    }

    private static void testPointGroups(PointGroups PG, List<Integer> firstPointSet, List<Integer> secondPointSet) {
        List<Integer> groups = PG.findGroups(firstPointSet, secondPointSet);
        System.out.println("Score: " + (double) (firstPointSet.size() / 2 - groups.stream().distinct().count() + 1) / (firstPointSet.size() / 2));
        System.out.println(groups);
    }
}