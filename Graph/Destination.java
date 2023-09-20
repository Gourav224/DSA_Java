/*

 * G-40: Number of Ways to Arrive at Destination
 * You are in a city that consists of n intersections numbered from 0 to n – 1 with bi-directional 
 * roads between some intersections. The inputs are generated such that you can reach any intersection 
 * from any other intersection and that there is at most one road between any two intersections.

 * You are given an integer n and a 2D integer array ‘roads’ where roads[i] = [ui, vi, timei] means 
 * that there is a road between intersections ui and vi that takes timei minutes to travel. You want 
 * to know in how many ways you can travel from intersection 0 to intersection n – 1 in the shortest 
 * amount of time.

 * Return the number of ways you can arrive at your destination in the shortest amount of time. Since 
 * the answer may be large, return it modulo 109 + 7.
 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Destination {
    static int countPaths(int n, List < List < Integer >> roads) {

        // Creating an adjacency list for the given graph.
        ArrayList < ArrayList < Pair >> adj = new ArrayList < > ();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList < > ());
        }
        int m = roads.size();
        for (int i = 0; i < m; i++) {
            adj.get(roads.get(i).get(0)).add(new Pair(roads.get(i).get(1), roads.get(i).get(2)));
            adj.get(roads.get(i).get(1)).add(new Pair(roads.get(i).get(0), roads.get(i).get(2)));
        }

        // Defining a priority queue (min heap). 
        PriorityQueue < Pair > pq = new PriorityQueue < Pair > ((x, y) -> x.first - y.first);

        // Initializing the dist array and the ways array
        // along with their first indices.
        int[] dist = new int[n];
        int[] ways = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            ways[i] = 0;
        }
        dist[0] = 0;
        ways[0] = 1;
        pq.add(new Pair(0, 0));

        // Define modulo value
        int mod = (int)(1e9 + 7);

        // Iterate through the graph with the help of priority queue
        // just as we do in Dijkstra's Algorithm.
        while (pq.size() != 0) {
            int dis = pq.peek().first;
            int node = pq.peek().second;
            pq.remove();

            for (Pair it : adj.get(node)) {
                int adjNode = it.first;
                int edW = it.second;

                // This ‘if’ condition signifies that this is the first
                // time we’re coming with this short distance, so we push
                // in PQ and keep the no. of ways the same.
                if (dis + edW < dist[adjNode]) {
                    dist[adjNode] = dis + edW;
                    pq.add(new Pair(dis + edW, adjNode));
                    ways[adjNode] = ways[node];
                } 

                    // If we again encounter a node with the same short distance
                    // as before, we simply increment the no. of ways.
                    else if (dis + edW == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }
        // Finally, we return the no. of ways to reach
        // (n-1)th node modulo 10^9+7.
        return ways[n - 1] % mod;
    }
    public static void main(String[] args) {

        int n = 7;
        List < List < Integer >> roads = new ArrayList < > () {
            {
                add(new ArrayList<Integer>(Arrays.asList(0, 6, 7)));
                add(new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
                add(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
                add(new ArrayList<Integer>(Arrays.asList(1, 3, 3)));
                add(new ArrayList<Integer>(Arrays.asList(6, 3, 3)));
                add(new ArrayList<Integer>(Arrays.asList(3, 5, 1)));
                add(new ArrayList<Integer>(Arrays.asList(6, 5, 1)));
                add(new ArrayList<Integer>(Arrays.asList(2, 5, 1)));
                add(new ArrayList<Integer>(Arrays.asList(0, 4, 5)));
                add(new ArrayList<Integer>(Arrays.asList(4, 6, 2)));

            }
        };
        
        int ans = countPaths(n, roads);

        System.out.print(ans);
        System.out.println();
    }
}
/*
 * Time Complexity: O( E* log(V)) { As we are using simple Dijkstra’s algorithm here,
 * the time complexity will be or the order E*log(V)}
 * Where E = Number of edges and V = No. of vertices.
 * Space Complexity :  O(N) { for dist array + ways array + approximate complexity for priority queue }
 * Where, N = Number of node
 */
