
package Recursion;

import java.util.Scanner;

public class RatInMaze {

    static boolean solveMaze(int[][] maze, int[][] path,
                             int row, int col, int n) {

        // Destination reached
        if (row == n - 1 && col == n - 1) {
            path[row][col] = 1;
            return true;
        }

        // Check boundaries and blocked cells
        if (row < 0 || col < 0 || row >= n || col >= n
                || maze[row][col] == 0
                || path[row][col] == 1) {
            return false;
        }

        // Mark current cell
        path[row][col] = 1;

        // Move down, right, up, left
        if (solveMaze(maze, path, row + 1, col, n)
                || solveMaze(maze, path, row, col + 1, n)
                || solveMaze(maze, path, row - 1, col, n)
                || solveMaze(maze, path, row, col - 1, n)) {
            return true;
        }

        // Backtrack
        path[row][col] = 0;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter maze size: ");
        int n = sc.nextInt();

        int[][] maze = new int[n][n];
        int[][] path = new int[n][n];

        System.out.println("Enter maze (1 = open, 0 = blocked):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        if (n > 0 && maze[0][0] == 1
                && solveMaze(maze, path, 0, 0, n)) {

            System.out.println("Path found:");
            for (int[] line : path) {
                for (int cell : line) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No path exists.");
        }

        sc.close();
    }
}
