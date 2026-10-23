/*For Iterative Binary Search, we use variables such as:

low
high
mid
target
These variables require only a constant amount of extra memory.
Therefore:Auxiliary Space Complexity = O(1)*/
package AssignmentOne;

public class BinarySearchSpaceComplexity {
        public static void main(String[] args) {

            int[] arr = {0, 2, 3, 4, 8, 61};
            int target = 4;

            int low = 0;
            int high = arr.length - 1;
            int index = -1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (arr[mid] == target) {
                    index = mid;
                    break;
                }
                else if (arr[mid] < target) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }

            if (index != -1) {
                System.out.println("Element found at index: " + index);
            } else {
                System.out.println("Element not found");
            }
        }

}
