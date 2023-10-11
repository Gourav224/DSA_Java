import java.util.*;

public class LongestDivisibleSubset {
    // Function to find the longest divisible subset
    static List<Integer> divisibleSet(List<Integer> arr) {
        int n = arr.size();

        // Sort the array
        Collections.sort(arr);

        int[] dp = new int[n];
        Arrays.fill(dp,1);
        int hash[] = new int[n];

        for (int i = 0; i < n; i++) {
            hash[i]=i; // Initializing with current index
            for (int prev_index = 0; prev_index <= i - 1; prev_index++) {
                if (arr.get(i) % arr.get(prev_index) == 0 && 1 + dp[prev_index] > dp[i]) {
                    dp[i]= 1 + dp[prev_index];
                    hash[i]= prev_index;
                }
            }
        }

        int ans = -1;
        int lastIndex = -1;

        for (int i = 0; i < n; i++) {
            if (dp[i] > ans) {
                ans = dp[i];
                lastIndex = i;
            }
        }

        List<Integer> temp = new ArrayList<>();
        temp.add(arr.get(lastIndex));

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            temp.add(arr.get(lastIndex));
        }

        // Reverse the array
        Collections.reverse(temp);

        return temp;
    }

    public static void main(String[] args) {

        List<Integer> arr = Arrays.asList(1, 16, 7, 8, 4);

        List<Integer> ans = divisibleSet(arr);

        System.out.print("The longest divisible subset elements are: ");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}
