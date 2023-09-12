import java.util.*;
// Graph -representation
public class Adjacency{
    public static void main(String[] args) {
        System.out.println("Graph");
        
        listMethod();

        matrixMethod();

    }
    static void listMethod(){
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        int n=3;
        int m=3;
        int[][] edges={{1,2},{1,3},{2,3}};
        // create list
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){

            adj.get(edges[i][0]).add(edges[i][1]);

            // for directed graph comment this line
            adj.get(edges[i][1]).add(edges[i][0]);
        }
    

        // Printlist
        for(int i=1;i<=n;i++){
            System.out.print(i);
            for(int j:adj.get(i)){
                System.out.print("->"+j);
            }
            System.out.println();
        }
    }

    //Matrix Method
    static void matrixMethod(){
        
        int V,E;
	    int[][] adjc;
        Scanner sc=new Scanner(System.in);
		V=sc.nextInt();
		E=sc.nextInt();
		adjc=new int[V+1][V+1];
        // create list
		for(int i=0;i<E;i++) {
			int u=sc.nextInt();
			int v=sc.nextInt();
			adjc[u][v]=1;
			adjc[u][v]=1;
		}
        sc.close();
        for(int i=0;i<=V;i++){
            for(int j=0;j<=V;j++){
                System.out.print(adjc[i][j]+" ");
            }
            System.out.println();
        }
    }
}