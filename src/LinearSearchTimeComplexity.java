/*The Best Case occurs when the target element is found at the first position of the array.
Target: 8 .Since 8 is the first element, Linear Search finds it after only one comparison.
Time Complexity: O(1)*/
/*The Worst Case occurs when the target element is at the last position or is not present in the array.
Target: 0
Array: {8, 61, 2, 3, 4, 0}
To find 0, Linear Search checks all 6 elements.Similarly, if the target is 5, which is not present, all elements must still be checked.
Time Complexity: O(n)*/
/*The Average Case occurs when the target is located at a random position in the array.
On average, Linear Search checks approximately half of the elements before finding the target.
For an array of n elements, the average number of comparisons is approximately:n / 2
 */
package AssignmentOne;

public class LinearSearchTimeComplexity {

        public static void linearSearch(int[] arr, int target) {

            int comparisons = 0;
            boolean found = false;

            for (int i = 0; i < arr.length; i++) {
                comparisons++;

                if (arr[i] == target) {
                    System.out.println("Target " + target + " found at index " + i);
                    System.out.println("Number of comparisons: " + comparisons);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Target " + target + " not found");
                System.out.println("Number of comparisons: " + comparisons);
            }

            System.out.println();
        }

        public static void main(String[] args) {

            int[] arr = {8, 61, 2, 3, 4, 0};

            System.out.println("Array: {8, 61, 2, 3, 4, 0}\n");

            System.out.println("BEST CASE:");
            linearSearch(arr, 8);

            System.out.println("AVERAGE CASE:");
            linearSearch(arr, 3);

            System.out.println("WORST CASE:");
            linearSearch(arr, 0);

            System.out.println("WORST CASE (Element Not Present):");
            linearSearch(arr, 5);
        }
    }

