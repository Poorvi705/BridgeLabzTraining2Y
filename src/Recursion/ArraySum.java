
package Recursion;

import java.util.*;

public class ArraySum {

    static int arraySum(int[] arr, int n) {
        // Base case
        if (n == 0) {
            return 0;
        }

        // Recursive case
        return arr[n - 1] + arraySum(arr, n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Sum = " + arraySum(arr, n));

        sc.close();
    }
}
