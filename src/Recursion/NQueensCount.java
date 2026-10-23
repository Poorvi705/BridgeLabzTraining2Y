package Recursion;

import java.util.Scanner;

public class NQueensCount {

    static boolean isSafe(char[][] board, int row, int col, int n) {
        // Check the same column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    static int countSolutions(char[][] board, int row, int n) {
        // All queens have been placed successfully
        if (row == n) {
            return 1;
        }

        int count = 0;

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';

                count += countSolutions(board, row + 1, n);

                // Backtrack
                board[row][col] = '.';
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("N must be positive.");
        } else {
            char[][] board = new char[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = '.';
                }
            }

            int total = countSolutions(board, 0, n);
            System.out.println("Total solutions = " + total);
        }

        sc.close();
    }
}