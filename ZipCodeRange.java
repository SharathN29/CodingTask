package com.demo;

/*
Complexity Analysis

Time complexity :O(nlogn)
Other than the sort invocation, we do a simple linear scan of the list, so the runtime is dominated by the O(nlogn) complexity of sorting.

Space complexity : O(1) (or O(n))
If we can sort intervals in place, we do not need more than constant additional space. Otherwise, we must allocate linear space to store a copy of intervals and sort that.

 */

import java.util.*;

public class ZipCodeRange {

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return null;

        if (intervals.length <= 1)
            return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);

        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                newInterval = interval;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        List<int[][]> intervals = new ArrayList<>();
        int[][] zipIntervals1 = {{94133,94133}, {94200,94299}, {94226,94399}};
        int[][] zipIntervals2 = {{98854, 98866},{98860,98870},{98770, 98880}};
        int[][] zipIntervals3 = null;
        int[][] zipIntervals4 = {{94133,94133},{94200,94299},{94600,94699}};
        intervals.add(zipIntervals1);
        intervals.add(zipIntervals2);
        intervals.add(zipIntervals3);
        intervals.add(zipIntervals4);


        for (int[][] interval : intervals) {
            int[][] res = merge(interval);
            System.out.println(Arrays.deepToString(res));
        }
    }
}
