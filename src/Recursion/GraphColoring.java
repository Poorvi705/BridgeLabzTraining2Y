
package Recursion;

import java.util.Scanner;
import java.util.Arrays;

public class GraphColoring {

    static boolean isSafe(int vertex, int[][] graph,
                          int[] colors, int color) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[vertex][i] == 1 && colors[i] == color) {
                return false;
            }
        }

        return true;
    }

    static boolean solve(int vertex, int[][] graph,
                         int[] colors, int m) {
        int n = graph.length;

        // Base case: all vertices are colored
        if (vertex == n) {
            return true;
        }

        // Try each available color
        for (int color = 1; color <= m; color++) {
            if (isSafe(vertex, graph, colors, color)) {

                // Make choice
                colors[vertex] = color;

                // Recurse for the next vertex
                if (solve(vertex + 1, graph, colors, m)) {
                    return true;
                }

                // Backtrack
                colors[vertex] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        System.out.print("Enter number of colors: ");
        int m = sc.nextInt();

        if (n <= 0 || m <= 0) {
            System.out.println("Vertices and colors must be positive.");
            sc.close();
            return;
        }

        int[][] graph = new int[n][n];

        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        int[] colors = new int[n];
        Arrays.fill(colors, 0);

        if (solve(0, graph, colors, m)) {
            System.out.println("Color assignment:");
            for (int i = 0; i < n; i++) {
                System.out.println(
                        "Vertex " + i + " -> Color " + colors[i]
                );
            }
        } else {
            System.out.println("No valid coloring exists.");
        }

        sc.close();
    }
}
