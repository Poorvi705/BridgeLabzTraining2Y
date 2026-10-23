
package Recursion;

import java.util.Scanner;

public class Permutations {

    static void generatePermutations(
            String str, String ans) {

        // Base case
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            // Remaining characters
            String remaining =
                    str.substring(0, i)
                            + str.substring(i + 1);

            // Recursive call
            generatePermutations(
                    remaining, ans + ch);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str = sc.next();

        System.out.println("Permutations:");
        generatePermutations(str, "");

        sc.close();
    }
}
