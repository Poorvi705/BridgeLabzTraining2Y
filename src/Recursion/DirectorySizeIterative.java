
package Recursion;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class DirectorySizeIterative {

    static long directorySize(File root) {
        if (root == null || !root.exists()) {
            return 0;
        }

        Deque<File> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        long total = 0;

        stack.push(root);

        while (!stack.isEmpty()) {
            File current = stack.pop();

            try {
                // Avoid processing the same canonical path twice
                String path = current.getCanonicalPath();

                if (!visited.add(path)) {
                    continue;
                }
            } catch (java.io.IOException e) {
                continue;
            }

            if (current.isFile()) {
                total += current.length();
            } else if (current.isDirectory()) {
                File[] children = current.listFiles();

                if (children != null) {
                    for (File child : children) {
                        stack.push(child);
                    }
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter directory path: ");
        String path = sc.nextLine();

        File root = new File(path);

        if (!root.exists()) {
            System.out.println("Path does not exist.");
        } else if (!root.isDirectory()) {
            System.out.println("Please enter a directory path.");
        } else {
            long size = directorySize(root);

            System.out.println("Total size: " + size + " bytes");
            System.out.printf(
                    "Total size: %.2f KB%n",
                    size / 1024.0
            );
        }

        sc.close();
    }
}
