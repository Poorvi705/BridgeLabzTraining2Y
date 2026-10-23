
package Recursion;

import java.io.File;
import java.util.Scanner;

public class DirectorySize {

    static long directorySize(File file) {
        if (file == null || !file.exists()) {
            return 0;
        }

        // Base case: a regular file
        if (file.isFile()) {
            return file.length();
        }

        // Recursive case: sum sizes of children
        File[] files = file.listFiles();

        if (files == null) {
            return 0;
        }

        long total = 0;

        for (File child : files) {
            total += directorySize(child);
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter directory path: ");
        String path = sc.nextLine();

        File directory = new File(path);

        if (!directory.exists()) {
            System.out.println("Path does not exist.");
        } else if (!directory.isDirectory()) {
            System.out.println("The path is not a directory.");
        } else {
            long bytes = directorySize(directory);
            System.out.println("Directory size: " + bytes + " bytes");
            System.out.printf(
                    "Directory size: %.2f MB%n",
                    bytes / (1024.0 * 1024.0)
            );
        }

        sc.close();
    }
}
