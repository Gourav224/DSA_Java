/*
 * Bridges in Graph – Using Tarjan’s Algorithm of time in and low time: G-55
 * Problem Statement: There are n servers numbered from 0 to n – 1 connected by undirected server-to-server 
 * connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. 
 * Any server can reach other servers directly or indirectly through the network.

 * A critical connection is a connection that, if removed, will make some servers unable to reach some other servers.

 * Return all critical connections in the network in any order.

 * Note: Here servers mean the nodes of the graph. The problem statement is taken from leetcode.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BridgesinGraph {
    private static  int timer = 1;
    private static void dfs(int node, int parent, int[] vis,ArrayList<ArrayList<Integer>> adj, int tin[], int low[],List<List<Integer>> bridges) {
        vis[node] = 1;
        tin[node] = low[node] = timer;
        timer++;
        for (Integer it : adj.get(node)) {
            if (it == parent) continue;
            if (vis[it] == 0) {
                dfs(it, node, vis, adj, tin, low, bridges);
                low[node] = Math.min(low[node], low[it]);
                // node --- it
                if (low[it] > tin[node]) {
                    bridges.add(Arrays.asList(it, node));
                }
            } else {
                low[node] = Math.min(low[node], low[it]);
            }
        }
    }
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<ArrayList<Integer>> adj =new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (List<Integer> it : connections) {
            int u = it.get(0); int v = it.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        List<List<Integer>> bridges = new ArrayList<>();
        dfs(0, -1, vis, adj, tin, low, bridges);
        return bridges;
    }

    public static void main (String[] args) {
        int n = 4;
        int[][] edges = {
            {0, 1}, {1, 2},
            {2, 0}, {1, 3}
        };
        List<List<Integer>> connections = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            connections.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; i++) {
            connections.get(i).add(edges[i][0]);
            connections.get(i).add(edges[i][1]);
        }

        List<List<Integer>> bridges = criticalConnections(n, connections);

        int size = bridges.size();
        for (int i = 0; i < size; i++) {
            int u = bridges.get(i).get(0);
            int v = bridges.get(i).get(1);
            System.out.print("[" + u + ", " + v + "] ");
        }
        System.out.println("");
    }
}
/*
 * Time Complexity: O(V+2E), where V = no. of vertices, E = no. of edges. It is because the algorithm is just a simple DFS traversal.

 * Space Complexity: O(V+2E) + O(3V), where V = no. of vertices, E = no. of edges. O(V+2E) to store the graph in an adjacency list 
 * and O(3V) for the three arrays i.e. tin, low, and vis, each of size V.
 */