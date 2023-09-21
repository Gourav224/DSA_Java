/*
 * Accounts Merge – DSU
 * Problem Statement: Given a list of accounts where each element account [ i ] is a list of 
 * strings, where the first element account [ i ][ 0 ]  is a name, and the rest of the elements 
 * are emails representing emails of the account.

 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person 
 * if there is some common email to both accounts. Note that even if two accounts have the same 
 * name, they may belong to different people as people could have the same name. A person can have 
 * any number of accounts initially, but all of their accounts definitely have the same name.

 * After merging the accounts, return the accounts in the following format: the first element of 
 * each account is the name, and the rest of the elements are emails in sorted order.

 * Note: Accounts themselves can be returned in any order.
 */

import java.util.*;

public class AccountsMerge {
     static List<List<String>> accountsMerge(List<List<String>> details) {
        int n = details.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> mapMailNode = new HashMap<String, Integer>();

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < details.get(i).size(); j++) {
                String mail = details.get(i).get(j);
                if (mapMailNode.containsKey(mail) == false) {
                    mapMailNode.put(mail, i);
                } else {
                    ds.unionBySize(i, mapMailNode.get(mail));
                }
            }
        }

        ArrayList<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++) mergedMail[i] = new ArrayList<String>();
        for (Map.Entry<String, Integer> it : mapMailNode.entrySet()) {
            String mail = it.getKey();
            int node = ds.findUPar(it.getValue());
            mergedMail[node].add(mail);
        }

        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (mergedMail[i].size() == 0) continue;
            Collections.sort(mergedMail[i]);
            List<String> temp = new ArrayList<>();
            temp.add(details.get(i).get(0));
            for (String it : mergedMail[i]) {
                temp.add(it);
            }
            ans.add(temp);
        }
        return ans;

    }
    public static void main (String[] args) {
        
        List<List<String>> accounts = new ArrayList() {
            {
                add(new ArrayList<String>(Arrays.asList("John", "j1@com", "j2@com", "j3@com")));
                add(new ArrayList<String>(Arrays.asList("John", "j4@com")));
                add(new ArrayList<String>(Arrays.asList("Raj", "r1@com", "r2@com")));
                add(new ArrayList<String>(Arrays.asList("John", "j1@com", "j5@com")));
                add(new ArrayList<String>(Arrays.asList("Raj", "r2@com", "r3@com")));
                add(new ArrayList<String>(Arrays.asList("Mary", "m1@com")));
            }
        };

        List<List<String>> ans = accountsMerge(accounts);

        int n = ans.size();
        for (int i = 0; i < n; i++) {
            System.out.print(ans.get(i).get(0) + ": ");
            int size = ans.get(i).size();
            for (int j = 1; j < size; j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }

            System.out.println("");
        }

    }
}
/*
 * Time Complexity: O(N+E) + O(E*4ɑ) + O(N*(ElogE + E)) where N = no. of indices or nodes and 
 * E = no. of emails. The first term is for visiting all the emails. The second term is for 
 * merging the accounts. And the third term is for sorting the emails and storing them in the answer array.

 * Space Complexity: O(N)+ O(N) +O(2N) ~ O(N) where N = no. of nodes/indices. The first and second 
 * space is for the ‘mergedMail’ and the ‘ans’ array. The last term is for the parent and size array 
 * used inside the Disjoint set data structure.
 */