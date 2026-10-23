package Recursion;

import java.util.Scanner;

public class NQueensForbidden {

    static boolean isSafe(char[][] board, int row, int col, int n) {
        // Check the column
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

    static boolean solve(char[][] board, int row, int n) {
        if (row == n) {
            return true;
        }

        for (int col = 0; col < n; col++) {

            // Forbidden squares cannot contain queens
            if (board[row][col] == 'X') {
                continue;
            }

            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';

                if (solve(board, row + 1, n)) {
                    return true;
                }

                // Backtrack without erasing forbidden squares
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
            sc.close();
            return;
        }

        char[][] board = new char[n][n];

        System.out.println("Enter the board row by row (use . and X):");

        for (int i = 0; i < n; i++) {
            String row = sc.next();

            if (row.length() != n ||
                    !row.matches("[.X]+")) {
                System.out.println("Invalid board row.");
                sc.close();
                return;
            }

            board[i] = row.toCharArray();
        }

        if (solve(board, 0, n)) {
            System.out.println("Solution:");

            for (char[] row : board) {
                for (char cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No valid solution exists.");
        }

        sc.close();
    }
}