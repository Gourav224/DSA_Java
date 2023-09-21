/*
 * Most Stones Removed with Same Row or Column – DSU: G-53
 * Problem Statement: There are n stones at some integer coordinate points on a 2D plane. 
 * Each coordinate point may have at most one stone.
 * You need to remove some stones. 
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 * Given an array of stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the 
 * maximum possible number of stones that you can remove.
 */

import java.util.HashMap;
import java.util.Map;

public class MostStonesRemoved {
    static  int maxRemove(int[][] stones, int n) {
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 1);
        HashMap<Integer, Integer> stoneNodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + maxRow + 1;
            ds.unionBySize(nodeRow, nodeCol);
            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }

        int cnt = 0;
        for (Map.Entry<Integer, Integer> it : stoneNodes.entrySet()) {
            if (ds.findUPar(it.getKey()) == it.getKey()) {
                cnt++;
            }
        }
        return n - cnt;
    }
    public static void main (String[] args) {
        int n = 6;
        int[][] stones = {
            {0, 0}, {0, 2},
            {1, 3}, {3, 1},
            {3, 2}, {4, 3}
        };

        int ans = maxRemove(stones, n);
        System.out.println("The maximum number of stones we can remove is: " + ans);
    }
}
/*
 * Time Complexity: O(N), where N = total no. of stones. Here we have just traversed the given stones array several times. And inside those loops, every operation is apparently taking constant time. So, the time complexity is only the time of traversal of the array.

 * Space Complexity: O(2* (max row index + max column index)) for the parent and size array inside the Disjoint Set data structure.
 */