package string;

public class LongestSubstringWithoutRepeatingLetters {

    public static void main(String[] args) {
        int longestSubstring = longestSubstringWithoutRepeatingLetters("abcdefghiiiiiii123456890");
        System.out.println(longestSubstring);
    }

    private static int longestSubstringWithoutRepeatingLetters(String word) {
        int n = word.length();
        int curr_len = 1;
        int max_len = 1;
        int visited[] = new int[256];
        visited[word.charAt(0)] = 0;

        for (int i = 1; i < n; i++) {
            int prevIndex = visited[word.charAt(i)];

            //if there is no previous instance of this letter word[i]
            // or if the previous index is NOT in the current Longest Substring Repeating Strings
            // then just increase the curr_index
            if (prevIndex == -1 || i - curr_len > prevIndex) {
                curr_len++;
            } else {
                max_len = Math.max(curr_len, max_len);
                curr_len = i - prevIndex;
            }
            visited[word.charAt(i)] = i;
            max_len = Math.max(curr_len, max_len);
        }

        return max_len;

    }
}
