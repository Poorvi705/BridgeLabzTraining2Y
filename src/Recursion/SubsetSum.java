
package Recursion;

import java.util.Scanner;
import java.util.ArrayList;

public class SubsetSum {

    static void findSubsets(int[] arr, int index,
                            int target, ArrayList<Integer> current) {

        // Base case: all elements considered
        if (index == arr.length) {
            if (target == 0) {
                System.out.println(current);
            }
            return;
        }

        // Include the current element
        current.add(arr[index]);
        findSubsets(arr, index + 1,
                target - arr[index], current);

        // Backtrack: remove the current element
        current.remove(current.size() - 1);

        // Exclude the current element
        findSubsets(arr, index + 1, target, current);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        System.out.println("Subsets with target sum:");
        findSubsets(arr, 0, target, new ArrayList<>());

        sc.close();
    }
}
