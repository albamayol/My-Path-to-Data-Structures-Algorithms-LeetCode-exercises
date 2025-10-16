import java.util.ArrayList;
import java.util.HashMap;

public class ExLongestSubstring {
    /**
     * Given a string s, find the length of the longest
     * substring
     *  without repeating characters.
     *
     * Example 1:
     *
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     *
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     *
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */


    public int lengthOfLongestSubstring(String s) {
        int i = 0, j, count = 0; //i --> indice inicial del substring; j -->
        ArrayList<Character> substring = new ArrayList<Character>();
        for(j = 1; j < s.length(); j++) {
            substring.add(s.charAt(j-1));
            if (!substring.contains(s.charAt(j))) {
                //i++;
                continue;
            } else { //contains
                //vaciar substring
                substring.clear();
                //incrementar indice substring
                i++;
                j = i;
            }
        }
        System.out.println("Substring: " + substring + "\n");
        return i;
    }

}
