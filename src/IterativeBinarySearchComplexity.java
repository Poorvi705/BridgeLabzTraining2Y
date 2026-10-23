/*Problem TC-2: Iterative Binary Search Complexity
 1. Step-by-step search range narrowing for target = 4:
 Sorted Array: {0, 2, 3, 4, 8, 61}
 Step 1:
 low = 0, high = 5
 mid = (0 + 5) / 2 = 2
 arr[2] = 3
 Since 3 < 4, search in the right half.
 low = mid + 1 = 3
 Step 2:
 low = 3, high = 5
 mid = (3 + 5) / 2 = 4
 arr[4] = 8
 Since 8 > 4, search in the left half.
 high = mid - 1 = 3
Step 3:
 low = 3, high = 3
 mid = (3 + 3) / 2 = 3
 arr[3] = 4
 Target 4 is found at index 3.
 2. Worst Case Time Complexity:
Binary Search divides the search space into half after every iteration: N -> N/2 -> N/4 -> N/8 -> ... -> N/(2^k)
 In the worst case, the search continues until only one element remains:
 N / (2^k) = 1
Therefore:
 N = 2^k
 k = log2(N)
Hence, the Worst Case Time Complexity of Binary Search is: O(log N)*/
package AssignmentOne;

public class IterativeBinarySearchComplexity {

        public static void binarySearch(int[] arr, int target) {

            int low = 0;
            int high = arr.length - 1;
            int comparisons = 0;

            while (low <= high) {

                int mid = (low + high) / 2;
                comparisons++;

                System.out.println("Step " + comparisons + ":");
                System.out.println("Low = " + low);
                System.out.println("High = " + high);
                System.out.println("Mid = " + mid);
                System.out.println("Middle Element = " + arr[mid]);

                if (arr[mid] == target) {
                    System.out.println("Target " + target +
                            " found at index " + mid);
                    System.out.println("Number of comparisons = " + comparisons);
                    return;

                } else if (arr[mid] < target) {
                    System.out.println(arr[mid] + " < " + target);
                    System.out.println("Searching in the right half\n");

                    low = mid + 1;

                } else {
                    System.out.println(arr[mid] + " > " + target);
                    System.out.println("Searching in the left half\n");

                    high = mid - 1;
                }
            }

            System.out.println("Target not found");
            System.out.println("Number of comparisons = " + comparisons);
        }

        public static void main(String[] args) {

            int[] arr = {0, 2, 3, 4, 8, 61};
            int target = 4;

            System.out.println("Sorted Array: {0, 2, 3, 4, 8, 61}");
            System.out.println("Target: " + target + "\n");

            binarySearch(arr, target);
        }
    }

