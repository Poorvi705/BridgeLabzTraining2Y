package Recursion;

import java.util.Scanner;

public class IterativeFactorial {

    public static long factorial(int n) {
        long fact = 1;

        for (int i = 1; i <= n; i++) {
            fact = fact * i;
        }

        return fact;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        if (n < 0 || n > 20) {
            System.out.println("Enter a number between 0 and 20.");
        } else {
            System.out.println("Factorial = " + factorial(n));
        }

        sc.close();
    }
}