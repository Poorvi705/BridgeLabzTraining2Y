
package Recursion;

import java.util.Scanner;

public class Fibonacci {

    static int fibonacci(int n) {
        // Base cases
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        // Recursive case
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms (0 to 40): ");
        int n = sc.nextInt();

        if (n < 0 || n > 40) {
            System.out.println("Enter a number from 0 to 40.");
        } else {
            System.out.println("Fibonacci series:");

            for (int i = 0; i < n; i++) {
                System.out.print(fibonacci(i) + " ");
            }

            System.out.println();
        }

        sc.close();
    }
}
