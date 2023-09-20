/*
 * Dijkstra’s Algorithm – Using Set 
 * Given a weighted, undirected, and connected graph of V vertices and an adjacency list adj
 * where adj[i] is a list of lists containing two integers where the first integer of each 
 * list j denotes there is an edge between i and j, second integers corresponds to the weight 
 * of that edge. You are given the source vertex S and You have to Find the shortest distance 
 * of all the vertex from the source vertex S. You have to return a list of integers denoting
 * the shortest distance between each node and Source vertex S.

 * Note: The Graph doesn’t contain any negative weight cycle
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    public static int[] dijkstra(int V, ArrayList<ArrayList<int[]>> adj, int S) {
        // Create a priority queue to store nodes as {dist, node}
        // where dist is the distance from the source to the node.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Initialize dist array with a large number to indicate
        // that nodes are unvisited initially.
        int[] dist = new int[V];
        Arrays.fill(dist, (int)(1e9));

        pq.add(new int[]{0, S}); // Source initialized with dist = 0
        dist[S] = 0;

        // Process nodes in the priority queue
        while (!pq.isEmpty()) {
            int[] nodeInfo = pq.poll();
            int node = nodeInfo[1];
            int dis = nodeInfo[0];

            // Check all adjacent nodes
            for (int[] neighborInfo : adj.get(node)) {
                int adjNode = neighborInfo[0];
                int edgeWeight = neighborInfo[1];

                if (dis + edgeWeight < dist[adjNode]) {
                    // If the current distance is smaller, update it
                    dist[adjNode] = dis + edgeWeight;
                    pq.add(new int[]{dist[adjNode], adjNode});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        // Driver code
        int V = 3, S = 2;
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        int[][] edges = {{1, 1}, {2, 6}, {2, 3}, {0, 1}, {1, 3}, {0, 6}};

        
        adj.get(0).add(edges[0]);
        adj.get(0).add(edges[1]);
        adj.get(1).add(edges[2]);
        adj.get(1).add(edges[3]);
        adj.get(2).add(edges[4]);
        adj.get(2).add(edges[5]);
        
        

        int[] res = dijkstra(V, adj, S);

        for (int i = 0; i < V; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }
}
/*
 * Time Complexity : O( E log(V) ) 
 * Space Complexity : O( |E| + |V| ) 
 * Where E = Number of edges and V = Number of Nodes.
 */