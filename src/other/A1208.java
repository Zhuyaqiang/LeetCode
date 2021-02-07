package other;

/**
 * 尽可能使字符串相等
 * 给你两个长度相同的字符串，s 和 t。
 *
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 *
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 *
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 *
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abcd", t = "bcdf", cost = 3
 * 输出：3
 * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
 * 示例 2：
 *
 * 输入：s = "abcd", t = "cdef", cost = 3
 * 输出：1
 * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
 * 示例 3：
 *
 * 输入：s = "abcd", t = "acde", cost = 0
 * 输出：1
 * 解释：你无法作出任何改动，所以最大长度为 1。
 *  
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 10^5
 * 0 <= maxCost <= 10^6
 * s 和 t 都只含小写英文字母。
 */
public class A1208 {
    public static void main(String[] args) {
        System.out.println(equalSubstring("abcd", "bcdf", 3)); // 3
        System.out.println(equalSubstring("abcd", "cdef", 3)); // 1
        System.out.println(equalSubstring("abcd", "cdef", 1)); // 0
        System.out.println(equalSubstring("abcd", "acde", 0)); // 1
        System.out.println(equalSubstring("krrgw", "zjxss", 19)); // 2
        System.out.println(equalSubstring("ujteygggjwxnfl", "nstsenrzttikoy", 43)); // 5
        System.out.println(equalSubstring("krpgjbjjznpzdfy", "nxargkbydxmsgby", 14)); // 4
    }
    public static int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        int currCost = 0, max = 0;
        int[] cost = new int[len];
        for (int i = 0; i < len; i++) {
            cost[i] = Math.abs(charS[i] - charT[i]);
        }
        int l = 0, r = 0;
        do {

            if (currCost + cost[r] <= maxCost) {
                currCost += cost[r];
                max = Math.max(max, r - l + 1);
                cost[r] = -cost[r];
                r++;
            } else {
                if (cost[l] < 0) {
                    currCost += cost[l];
                    cost[l] = -cost[l];
                }
                l++;
                if (l > r) {
                    r = l;
                }
            }
        } while (r < len);

        return max;
    }
}
