
package Recursion;

import java.util.Scanner;

public class Factorial {

    static long factorial(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }

        // Recursive case
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a non-negative integer (0 to 20): ");
        int n = sc.nextInt();

        if (n < 0 || n > 20) {
            System.out.println("Please enter a number from 0 to 20.");
        } else {
            System.out.println("Factorial = " + factorial(n));
        }

        sc.close();
    }
}
