/*
 * Remove Duplicates in-place from Sorted Array
 * Problem Statement: Given an integer array sorted in non-decreasing order, remove the duplicates in place 
 * such that each unique element appears only once. The relative order of the elements should be kept the same.
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int arr[] = { 1, 1, 2, 2, 2, 3, 3 };
        int k = removeDuplicates(arr);
        System.out.println("The array after removing duplicate elements is ");
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static int removeDuplicates(int[] arr) {
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if (arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1;
    }
}
