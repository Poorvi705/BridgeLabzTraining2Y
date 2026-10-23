package Recursion;

import java.util.*;

public class InsertInterval {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Add intervals that come before the new interval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Merge all overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // Add the merged interval
        result.add(newInterval);

        // Add the remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of intervals: ");
        int n = sc.nextInt();

        int[][] intervals = new int[n][2];

        System.out.println("Enter each interval's start and end:");
        for (int i = 0; i < n; i++) {
            intervals[i][0] = sc.nextInt();
            intervals[i][1] = sc.nextInt();
        }

        int[] newInterval = new int[2];
        System.out.println("Enter the new interval's start and end:");
        newInterval[0] = sc.nextInt();
        newInterval[1] = sc.nextInt();

        int[][] answer = insert(intervals, newInterval);

        System.out.println("Intervals after insertion:");
        for (int[] interval : answer) {
            System.out.println(
                    "[" + interval[0] + ", " + interval[1] + "]"
            );
        }

        sc.close();
    }
}