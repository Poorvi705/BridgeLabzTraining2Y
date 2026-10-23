
package Recursion;

import java.util.Scanner;

public class OptimizedPower {

    static double power(double x, int n) {
        long exp = n;

        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }

        return fastPower(x, exp);
    }

    static double fastPower(double x, long n) {
        if (n == 0) {
            return 1;
        }

        double half = fastPower(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter base: ");
        double x = sc.nextDouble();

        System.out.print("Enter exponent: ");
        int n = sc.nextInt();

        if (x == 0.0 && n < 0) {
            System.out.println("Undefined result");
        } else {
            System.out.println("Result: " + power(x, n));
        }

        sc.close();
    }
}
