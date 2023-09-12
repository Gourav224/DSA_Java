import java.util.*;

public class WordLadder1 {
    public static void main(String[] args)  {
        String startWord = "der", targetWord = "dfs";
        String[] wordList = {
            "des",
            "der",
            "dfr",
            "dgt",
            "dfs"
        };

        WordLadder1Solution obj = new WordLadder1Solution();
        int ans = obj.wordLadderLength(startWord, targetWord, wordList);

        System.out.print(ans);

        System.out.println();
    }
}
class Pair1 {
    String first;
    int second;
    Pair1(String _first, int _second) {
        this.first = _first;
        this.second = _second;
    }
}
class WordLadder1Solution {
    public int wordLadderLength(String startWord, String targetWord, String[] wordList) {
        // Creating a queue ds of type {word,transitions to reach ‘word’}.
        Queue < Pair1 > q = new LinkedList < > ();
 
        // BFS traversal with pushing values in queue 
        // when after a transformation, a word is found in wordList.
        q.add(new Pair1(startWord, 1));

        // Push all values of wordList into a set
        // to make deletion from it easier and in less time complexity.
        Set < String > st = new HashSet < String > ();
        int len = wordList.length;
        for (int i = 0; i < len; i++) {
            st.add(wordList[i]);
        }
        st.remove(startWord);
        while (!q.isEmpty()) {
            String word = q.peek().first;
            int steps = q.peek().second;
            q.remove();
      
            // we return the steps as soon as
            // the first occurence of targetWord is found.
            if (word.equals(targetWord) == true) return steps;

            // Now, replace each character of ‘word’ with char
            // from a-z then check if ‘word’ exists in wordList.
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char replacedCharArray[] = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);

                    // check if it exists in the set and push it in the queue.
                    if (st.contains(replacedWord) == true) {
                        st.remove(replacedWord);
                        q.add(new Pair1(replacedWord, steps + 1));
                    }
                }

            }
        }
        // If there is no transformation sequence possible
        return 0;
    }
}
// Time Complexity: O(N * M * 26)