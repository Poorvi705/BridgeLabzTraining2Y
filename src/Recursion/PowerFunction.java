
package Recursion;

import java.util.Scanner;

public class PowerFunction {

    static double power(double x, int n) {
        // Base case
        if (n == 0) {
            return 1;
        }

        // Handle negative exponent
        if (n < 0) {
            return 1 / power(x, -(n + 1)) / x;
        }

        // Recursive call
        return x * power(x, n - 1);
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
