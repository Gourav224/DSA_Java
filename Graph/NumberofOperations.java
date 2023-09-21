/*
 * Number of Operations to Make Network Connected – DSU:
 * Problem Statement: You are given a graph with n vertices and m edges.
 * You can remove one edge from anywhere and add that edge between any 
 * two vertices in one operation. Find the minimum number of operations 
 * that will be required to make the graph connected. If it is not 
 * ossible to make the graph connected, return -1.
 */
public class NumberofOperations {
    public static int Solve(int n, int[][] edge) {
        DisjointSet ds = new DisjointSet(n);
        int cntExtras = 0;
        int m = edge.length;
        for (int i = 0; i < m ; i++) {
            int u = edge[i][0];
            int v = edge[i][1];
            if (ds.findUPar(u) == ds.findUPar(v)) {
                cntExtras++;
            } else {
                ds.unionBySize(u, v);
            }
        }
        int cntC = 0;
        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) cntC++;
        }
        int ans = cntC - 1;
        if (cntExtras >= ans) return ans;
        return -1;
    }

    public static void main (String[] args) {
        int V = 9;
        int[][] edge = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {2, 3}, {4, 5}, {5, 6}, {7, 8}};

        int ans = Solve(V, edge);
        System.out.println("The number of operations needed: " + ans);

    }
}
/*
 * Time Complexity: O(E*4α)+O(N*4α) where E = no. of edges and N = no. of nodes. The first term is to calculate the number of extra edges and the second term is to count the number of components. 4α is for the disjoint set operation we have used and this term is so small that it can be considered constant.

 * Space Complexity: O(2N) where N = no. of nodes. 2N for the two arrays(parent and size) of size N we have used inside the disjoint set.
 */
