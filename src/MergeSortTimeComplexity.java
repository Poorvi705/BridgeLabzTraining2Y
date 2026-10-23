// Problem TC-3: Merge Sort Time Complexity
//
// Sample Array: {8, 61, 2, 3, 4, 0}
//
// 1. Division / Split Tree:
//
//                    {8, 61, 2, 3, 4, 0}
//                           /        \
//                  {8, 61, 2}        {3, 4, 0}
//                    /     \           /     \
//                {8}      {61, 2}    {3}     {4, 0}
//                          /   \              /   \
//                        {61}  {2}           {4}   {0}
//
// Final individual elements:
// {8}, {61}, {2}, {3}, {4}, {0}
//
// 2. Recurrence Relation:
//
// T(N) = 2T(N/2) + O(N)
//
// At each level of the recursion tree:
//
// Level 0: 1 array      -> Total work = O(N)
// Level 1: 2 subarrays  -> Total work = O(N)
// Level 2: 4 subarrays  -> Total work = O(N)
//
// The array is divided into halves until individual elements remain.
// Therefore, the number of levels is approximately log2(N).
//
// Total Work = O(N) × O(log N)
//
// Final Time Complexity = O(N log N)
package AssignmentOne;

public class MergeSortTimeComplexity {
        public static void merge(int[] arr, int left, int mid, int right) {

            int n1 = mid - left + 1;
            int n2 = right - mid;

            int[] leftArray = new int[n1];
            int[] rightArray = new int[n2];


            for (int i = 0; i < n1; i++) {
                leftArray[i] = arr[left + i];
            }
            for (int j = 0; j < n2; j++) {
                rightArray[j] = arr[mid + 1 + j];
            }

            int i = 0;
            int j = 0;
            int k = left;


            while (i < n1 && j < n2) {

                if (leftArray[i] <= rightArray[j]) {
                    arr[k] = leftArray[i];
                    i++;
                } else {
                    arr[k] = rightArray[j];
                    j++;
                }

                k++;
            }
            while (i < n1) {
                arr[k] = leftArray[i];
                i++;
                k++;
            }


            while (j < n2) {
                arr[k] = rightArray[j];
                j++;
                k++;
            }
        }



        public static void mergeSort(int[] arr, int left, int right) {

            if (left < right) {

                int mid = (left + right) / 2;


                mergeSort(arr, left, mid);
                mergeSort(arr, mid + 1, right);
                merge(arr, left, mid, right);
            }
        }


        public static void main(String[] args) {

            int[] arr = {8, 61, 2, 3, 4, 0};

            System.out.println("Original Array:");

            for (int num : arr) {
                System.out.print(num + " ");
            }


            mergeSort(arr, 0, arr.length - 1);

            System.out.println("\n\nSorted Array:");

            for (int num : arr) {
                System.out.print(num + " ");
            }
        }
    }

