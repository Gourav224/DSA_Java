/*
 * Search in a sorted 2D matrix
 * Problem Statement: You have been given a 2-D array ‘mat’ of size ‘N x M’ where ‘N’ and ‘M’ denote 
 * the number of rows and columns, respectively. The elements of each row are sorted in non-decreasing 
 * order. Moreover, the first element of a row is greater than the last element of the previous row 
 * (if it exists). You are given an integer ‘target’, and your task is to find if it exists in the 
 * given ‘mat’ or not.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Search {
    public static boolean searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target) {
        int n = matrix.size();
        int m = matrix.get(0).size();

        // apply binary search:
        int low = 0, high = n * m - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int row = mid / m, col = mid % m;
            if (matrix.get(row).get(col) == target)
                return true;
            else if (matrix.get(row).get(col) < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        matrix.add(new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        matrix.add(new ArrayList<>(Arrays.asList(9, 10, 11, 12)));

        boolean result = searchMatrix(matrix, 8);
        System.out.println(result ? "true" : "false");
    }
}
