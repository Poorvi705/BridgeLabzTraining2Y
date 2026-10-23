
package Recursion;

import java.util.Scanner;

public class WordSearch {

    static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean search(char[][] board, String word,
                          int row, int col, int index) {

        // All characters matched
        if (index == word.length()) {
            return true;
        }

        // Check boundaries and character
        if (row < 0 || col < 0 ||
                row >= board.length ||
                col >= board[0].length ||
                board[row][col] != word.charAt(index)) {
            return false;
        }

        // Mark cell as visited
        char original = board[row][col];
        board[row][col] = '#';

        boolean found =
                search(board, word, row + 1, col, index + 1) ||
                        search(board, word, row - 1, col, index + 1) ||
                        search(board, word, row, col + 1, index + 1) ||
                        search(board, word, row, col - 1, index + 1);

        // Backtrack: restore the cell
        board[row][col] = original;

        return found;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter rows and columns: ");
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        char[][] board = new char[rows][cols];

        System.out.println("Enter grid characters:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = sc.next().charAt(0);
            }
        }

        System.out.print("Enter word to search: ");
        String word = sc.next();

        System.out.println(
                exist(board, word) ? "Word found" : "Word not found"
        );

        sc.close();
    }
}
