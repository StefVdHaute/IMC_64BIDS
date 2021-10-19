package main;

import round2.combinations.OptionCombinations;
import round2.datacenter.DataCenterConnections;
import round2.subsequence.ShortestSubsequence;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        OptionCombinations oc = new OptionCombinations();
        System.out.println(oc.countCombinations(1, 1));
        System.out.println(oc.countCombinations(2, 1));
        System.out.println(oc.countCombinations(3, 2));
        System.out.println(oc.countCombinations(1000, 50));

        assert oc.countCombinations(1, 1).equals("1");
        assert oc.countCombinations(2, 1).equals("11");
        assert oc.countCombinations(3, 2).equals("4083");

        System.out.println("----------------------------------------------------------------------");

        DataCenterConnections dc = new DataCenterConnections();

        int n = 5;
        List<List<Integer>> microwaveConnections = List.of(List.of(1, 4), List.of(4, 5));
        List<List<Integer>> fiberConnections = List.of(List.of(1, 2), List.of(3, 4), List.of(4, 5));

        System.out.println(dc.findShortestDistance(n, microwaveConnections, fiberConnections));

        n = 5;
        microwaveConnections = List.of(List.of(1, 4), List.of(4, 5));
        fiberConnections = List.of(List.of(2, 3), List.of(2, 5), List.of(3, 4));

        System.out.println(dc.findShortestDistance(n, microwaveConnections, fiberConnections));

        System.out.println("----------------------------------------------------------------------");

        ShortestSubsequence ss = new ShortestSubsequence();

        List<String> names = List.of("JAN21PUT110", "JAN21PUT120", "JUN21PUT120", "JAN22PUT110", "JAN22PUT120");
        System.out.println(String.join(", ", ss.findShortVersions(names)));

        names = List.of("APPLE", "AAPL", "PEPEPLE");
        System.out.println(String.join(", ", ss.findShortVersions(names)));

        names = List.of("BABAB", "BABBA");
        System.out.println(String.join(", ", ss.findShortVersions(names)));

        names = List.of("BABBA", "BABAB");
        System.out.println(String.join(", ", ss.findShortVersions(names)));
    }
}