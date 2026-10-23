
package Recursion;

import java.util.Scanner;

public class SudokuSolver {

    static boolean isSafe(int[][] board, int row,
                          int col, int num) {

        // Check row and column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num ||
                    board[i][col] == num) {
                return false;
            }
        }

        // Check 3 x 3 box
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean solveSudoku(int[][] board) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                // Find an empty cell
                if (board[row][col] == 0) {

                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {

                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        // No empty cells remain
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[9][9];

        System.out.println(
                "Enter Sudoku (use 0 for empty cells):");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        if (solveSudoku(board)) {
            System.out.println("Solved Sudoku:");

            for (int[] row : board) {
                for (int num : row) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution exists.");
        }

        sc.close();
    }
}
