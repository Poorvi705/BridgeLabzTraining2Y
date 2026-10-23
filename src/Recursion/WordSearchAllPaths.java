
package Recursion;

import java.util.Scanner;

public class WordSearchAllPaths {

    static int pathCount = 0;

    static void findAllPaths(char[][] board, String word,
                             int row, int col, int index,
                             String path) {

        // All characters matched
        if (index == word.length()) {
            pathCount++;
            System.out.println("Path " + pathCount + ": " + path);
            return;
        }

        // Check boundaries and matching character
        if (row < 0 || col < 0 ||
                row >= board.length ||
                col >= board[0].length ||
                board[row][col] != word.charAt(index)) {
            return;
        }

        // Mark the cell as visited
        char original = board[row][col];
        board[row][col] = '#';

        String current = path + "(" + row + "," + col + ") ";

        // Explore all four directions
        findAllPaths(board, word, row + 1, col,
                index + 1, current);

        findAllPaths(board, word, row - 1, col,
                index + 1, current);

        findAllPaths(board, word, row, col + 1,
                index + 1, current);

        findAllPaths(board, word, row, col - 1,
                index + 1, current);

        // Backtrack: restore the cell
        board[row][col] = original;
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

        pathCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                findAllPaths(board, word, i, j, 0, "");
            }
        }

        if (pathCount == 0) {
            System.out.println("No paths found.");
        } else {
            System.out.println("Total paths: " + pathCount);
        }

        sc.close();
    }
}
