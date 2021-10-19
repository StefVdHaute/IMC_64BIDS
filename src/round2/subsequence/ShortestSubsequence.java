package round2.subsequence;

import java.util.*;

public class ShortestSubsequence {
    public List<String> findShortVersions(List<String> names) {
        return findShortVersions(names.get(0), names.get(1));
    }

    private List<String> findShortVersions(String s, String t) {
        int m = s.length();
        int n = t.length();

        // declaring 2D array of m + 1 rows and
        // n + 1 columns dynamically
        int[][] dp = new int[m + 1][n + 1];

        // T string is empty
        for (int i = 1; i <= m; i++) {// populate first column with 1
            dp[i][0] = 1;
        }

        // S string is empty
        Arrays.fill(dp[0], Math.max(m, n));// populate first row with MAX

        for (int i = 1; i <= m; i++) {// for characters of s
            for (int j = 1; j <= n; j++) {// for characters of t
                char ch = s.charAt(i - 1);
                int k = j - 1;

                while (k >= 0 && t.charAt(k) != ch) {// find first occurrence of ch between 0 and j - 1 in t if not -1
                    k--;
                }

                if (k == -1) {// char not present in subset 0 - (j-1) of T
                    dp[i][j] = 1;
                } else {// char at position k of T
                    dp[i][j] = Math.min(dp[i - 1][j],
                            dp[i - 1][k] + 1);
                }
            }
        }

        char[] left = {};
        char[] right = {};

        int ans = dp[m][n];
        if (ans >= Math.max(m, n)) {
            ans = -1;
        } else {
            left = new char[ans];
            right = new char[ans];

            int i = m, j = n;
            int index = dp[m][n];

            while (index > 0) {
                if (dp[i][j - 1] == index) {// left is equal
                    j--;
                    if (j == 0) {
                        index--;
                        left[index] = s.charAt(i-1);
                        // loop should normally end here
                    }
                } else if (dp[i - 1][j] == index) {// up is equal
                    i--;
                } else if (dp[i - 1][j - 1] == index){// diagonal equal (diagonal NW should be less)
                    i--;
                    j--;
                } else {// diagonal less (diagonal NW should always be less)
                    i--;
                    j--;
                    index--;
                    left[index] = s.charAt(i);
                }
            }
        }

        return List.of(String.valueOf(left), String.valueOf(right));
    }
}