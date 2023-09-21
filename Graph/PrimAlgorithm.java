/*
 * Prim’s Algorithm – Minimum Spanning Tree – C++ and Java
 * Problem Statement: Given a weighted, undirected, and connected graph of V vertices and E edges. 
 * The task is to find the sum of weights of the edges of the Minimum Spanning Tree.
 * (Sometimes it may be asked to find the MST as well, where in the MST the edge-informations will 
 * be stored in the form {u, v}(u = starting node, v = ending node).)
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimAlgorithm {
     static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {

        PriorityQueue<Pair> pq =new PriorityQueue<Pair>((x, y) -> x.first - y.first);

        int[] vis = new int[V];
        // {wt, node}
        pq.add(new Pair(0, 0));
        int sum = 0;
        while (pq.size() > 0) {
            int wt = pq.peek().first;
            int node = pq.peek().second;
            pq.remove();

            if (vis[node] == 1) continue;
            // add it to the mst
            vis[node] = 1;
            sum += wt;

            for (int i = 0; i < adj.get(node).size(); i++) {
                int edW = adj.get(node).get(i).get(1);
                int adjNode = adj.get(node).get(i).get(0);
                if (vis[adjNode] == 0) {
                    pq.add(new Pair(edW, adjNode));
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) {
    int V = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
        int[][] edges =  {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<ArrayList<Integer>>());
        }

        for (int i = 0; i < 6; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            ArrayList<Integer> tmp1 = new ArrayList<Integer>();
            ArrayList<Integer> tmp2 = new ArrayList<Integer>();
            tmp1.add(v);
            tmp1.add(w);

            tmp2.add(u);
            tmp2.add(w);

            adj.get(u).add(tmp1);
            adj.get(v).add(tmp2);
        }

      
        int sum = spanningTree(V, adj);
        System.out.println("The sum of all the edge weights: " + sum);
    }
}
/*
 * Time Complexity: O(E*logE) + O(E*logE)~ O(E*logE), where E = no. of given edges.

 * Space Complexity: O(E) + O(V), where E = no. of edges and V = no. of vertices. O(E) occurs due to the size of the priority queue and O(V) due to the visited array. If we wish to get the mst, we need an extra O(V-1) space to store the edges of the most.
 */