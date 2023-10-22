/*
 * Find the number that appears once, and the other numbers twice
 * Problem Statement: Given a non-empty array of integers arr, every element appears twice except for one. Find that single one.
 */
public class NumberAppearsOnce {
    public static int getSingleElement(int[] arr) {
        // size of the array:
        int n = arr.length;

        // XOR all the elements:
        int xorr = 0;
        for (int i = 0; i < n; i++) {
            xorr = xorr ^ arr[i];
        }
        return xorr;
    }

    public static void main(String args[]) {
        int[] arr = { 4, 1, 2, 1, 2 };
        int ans = getSingleElement(arr);
        System.out.println("The single element is: " + ans);

    }
}
