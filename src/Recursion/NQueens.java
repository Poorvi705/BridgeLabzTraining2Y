package Recursion;

import java.util.Scanner;

public class NQueens {

    static boolean isSafe(char[][] board, int row, int col, int n) {
        // Check the same column above the current row
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check the upper-left diagonal
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check the upper-right diagonal
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    static boolean solveNQueens(char[][] board, int row, int n) {
        // All queens have been placed
        if (row == n) {
            return true;
        }

        // Try placing a queen in each column
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';

                if (solveNQueens(board, row + 1, n)) {
                    return true;
                }

                // Backtrack
                board[row][col] = '.';
            }
        }

        return false;
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

            if (solveNQueens(board, 0, n)) {
                System.out.println("One valid solution:");

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(board[i][j] + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution exists.");
            }
        }

        sc.close();
    }
}