/*
 * Find the City With the Smallest Number of Neighbours at a Threshold Distance: 
 * Problem Statement: There are n cities numbered from 0 to n-1. Given the array edges
 *  where edges[i] = [fromi, toi,weighti]  represents a bidirectional and weighted edge 
 * between cities fromi and toi, and given the integer distance Threshold. You need to 
 * find out a city with the smallest number of cities that are reachable through some
 *  path and whose distance is at most Threshold Distance, If there are multiple such 
 * cities, our answer will be the city with the greatest number.

 * Note: that the distance of a path, connecting cities i and j are equal to the sum of the edges’ weights along that path.
 * 
 */

public class ThresholdDistance {
    static int findCity(int n, int m, int edges[][],
                 int distanceThreshold) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                dist[i][j] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            dist[u][v] = wt;
            dist[v][u] = wt;
        }

        for (int i = 0; i < n; i++) dist[i][i] = 0;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE ||
                            dist[k][j] == Integer.MAX_VALUE)
                        continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int cntCity = n;
        int cityNo = -1;
        for (int city = 0; city < n; city++) {
            int cnt = 0;
            for (int adjCity = 0; adjCity < n; adjCity++) {
                if (dist[city][adjCity] <= distanceThreshold)
                    cnt++;
            }

            if (cnt <= cntCity) {
                cntCity = cnt;
                cityNo = city;
            }
        }
        return cityNo;
    }

    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        int[][] edges =  {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold = 4;

      
        int cityNo = findCity(n, m, edges, distanceThreshold);
        System.out.println("The answer is node: " + cityNo);
    }
}
/*
 * Time Complexity: O(V3), as we have three nested loops each running for V times, where V = no. of vertices.

 * Space Complexity: O(V2), where V = no. of vertices. This space complexity is due to storing the adjacency matrix of the given graph.
 
 */