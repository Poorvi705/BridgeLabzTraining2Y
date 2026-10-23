/*1. Explanation of Merge Sort Extra Memory

Merge Sort divides the array into smaller subarrays and then merges them in sorted order. During merging, a temporary array is required to store the sorted elements before copying them back into the original array. For an input array of size n, this temporary array can require n extra spaces.

Therefore, the auxiliary space complexity of Merge Sort is O(n).
2. Comparison with In-Place Sorting Algorithms

Merge Sort requires O(n) extra memory because of the temporary array used during merging.

In comparison, Insertion Sort is an in-place sorting algorithm and requires only O(1) auxiliary space. Quick Sort is also generally performed in-place and requires O(log n) auxiliary space on average due to recursive function calls.

Therefore, in terms of space efficiency:

Insertion Sort O(1) > Quick Sort O(log n) > Merge Sort O(n)

Hence, Merge Sort is less space-efficient than Insertion Sort and typical in-place Quick Sort.
 */
package AssignmentOne;

public class MergeSortSpaceComplexity {

        public static void mergeSort(int[] arr, int low, int high) {

            if (low < high) {

                int mid = low + (high - low) / 2;

                // Divide the array
                mergeSort(arr, low, mid);
                mergeSort(arr, mid + 1, high);

                // Merge the divided arrays
                merge(arr, low, mid, high);
            }
        }

        public static void merge(int[] arr, int low, int mid, int high) {

            int[] temp = new int[high - low + 1];

            int i = low;
            int j = mid + 1;
            int k = 0;

            // Compare elements from both halves
            while (i <= mid && j <= high) {

                if (arr[i] <= arr[j]) {
                    temp[k] = arr[i];
                    i++;
                } else {
                    temp[k] = arr[j];
                    j++;
                }

                k++;
            }

            // Copy remaining elements from left half
            while (i <= mid) {
                temp[k] = arr[i];
                i++;
                k++;
            }

            // Copy remaining elements from right half
            while (j <= high) {
                temp[k] = arr[j];
                j++;
                k++;
            }

            // Copy sorted elements back to original array
            for (i = low, k = 0; i <= high; i++, k++) {
                arr[i] = temp[k];
            }
        }

        public static void main(String[] args) {

            int[] arr = {8, 61, 2, 3, 4, 0};

            mergeSort(arr, 0, arr.length - 1);

            System.out.println("Sorted Array:");

            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }

}
