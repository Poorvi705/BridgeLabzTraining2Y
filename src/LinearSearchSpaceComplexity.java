/*The algorithm only uses a few variables, such as:
i → loop counter
key → element being searched
Possibly n → array size
It does not create any additional array or data structure.
Therefore, the extra memory used remains constant regardless of the input array size.
Auxiliary Space Complexity = O(1)*/
package AssignmentOne;

public class LinearSearchSpaceComplexity {
        public static void main(String[] args) {

            int[] arr = {8, 61, 2, 3, 4, 0};
            int target = 3;

            int index = -1;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == target) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                System.out.println("Element found at index: " + index);
            } else {
                System.out.println("Element not found");
            }
        }
    }

