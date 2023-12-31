/*

 *  Minimum Multiplications to Reach End
 * Given start, end, and an array arr of n numbers. At each step, the start is multiplied by 
 * any number in the array and then a mod operation with 100000 is done to get the new start.
 * Your task is to find the minimum steps in which the end can be achieved starting from the 
 * start. If it is not possible to reach the end, then return -1.
 * 
 */

import java.util.LinkedList;
import java.util.Queue;

public class MinimumMultiplications  {
    static int minimumMultiplications(int[] arr, int start, int end) {

        // Create a queue for storing the numbers as a result of multiplication
        // of the numbers in the array and the start number.
        Queue<Pair> q = new LinkedList<>(); 
        q.add(new Pair(start, 0)); 

        // Create a dist array to store the no. of multiplications to reach
        // a particular number from the start number.
        int[] dist = new int[100000]; 
        for(int i = 0;i<100000;i++) dist[i] = (int)(1e9);
        dist[start] = 0; 
        int mod = 100000;
        int n = arr.length; 
        // O(100000 * N) 

        // Multiply the start no. with each of numbers in the arr
        // until we get the end no.
        while(!q.isEmpty()) {
            int node = q.peek().first; 
            int steps = q.peek().second;
            q.remove(); 
            
            for(int i = 0;i < n; i++) {
                int num = (arr[i] * node) % mod; 

                // If the no. of multiplications are less than before
                // in order to reach a number, we update the dist array.
                if(steps + 1 < dist[num]) {
                    dist[num] = steps + 1; 

                    // Whenever we reach the end number
                    // return the calculated steps
                    if(num == end) return steps + 1; 
                    q.add(new Pair(num, steps + 1)); 
                }
            }
        }
        // If the end no. is unattainable.
        return -1; 
    }
    public static void main(String[] args) {
       
        int start=3, end=30;
        int[] arr = {2,5,7};

        
        int ans =minimumMultiplications(arr,start,end);
        
        System.out.print(ans);
        System.out.println();
    }
}
/*
 * Time Complexity : O(100000 * N) 

 * Where ‘100000’ are the total possible numbers generated by multiplication (hypothetical) and N = size
 *  of the array with numbers of which each node could be multiplied.

 * Space Complexity :  O(100000 * N) 

 * Where ‘100000’ are the total possible numbers generated by multiplication (hypothetical) and N = size of
 *  the array with numbers of which each node could be multiplied. 100000 * N is the max possible queue size.
 *  The space complexity of the dist array is constant.
 */