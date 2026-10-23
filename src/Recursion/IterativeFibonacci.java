package Recursion;

import java.util.Scanner;

public class IterativeFibonacci {

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        int first = 0;
        int second = 1;

        for (int i = 2; i <= n; i++) {
            int next = first + second;
            first = second;
            second = next;
        }

        return second;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the position: ");
        int n = sc.nextInt();

        if (n < 0 || n > 46) {
            System.out.println("Enter a position between 0 and 46.");
        } else {
            System.out.println("Fibonacci number = " + fibonacci(n));
        }

        sc.close();
    }
}