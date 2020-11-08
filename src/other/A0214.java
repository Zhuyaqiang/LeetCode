package other;

/**
 * 最短回文串
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * 示例 1:
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 * 示例 2:
 * 输入: "abcd"
 * 输出: "dcbabcd"
 */
public class A0214 {
    public static void main(String[] args) {
        System.out.println(shortestPalindrome("abcdef"));
    }
    // 原字符串 abbacd
    //先判断 abbacd 是不是回文串, 发现不是, 执行下一步
    //判断 abbac 是不是回文串, 发现不是, 执行下一步
    //判断 abba 是不是回文串, 发现是，将末尾的 2 个字符 cd 倒置后加到原字符串的头部,
    //即 dcabbacd
    //
    //作者：windliang
    //链接：https://leetcode-cn.com/problems/shortest-palindrome/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--44/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public static String rShortestPalindrome1(String s) {
        int r = s.length() - 1;
        while (r >= 0) {
            if (check(s, 0, r)) {
                break;
            } else {
                r --;
            }
        }
        StringBuilder sb = new StringBuilder(s);
        r++;
        while (r < s.length()) {
            sb.insert(0, s.charAt(r++));
        }
        return sb.toString();
    }
    public static boolean check(String s, int l, int r) {
        if (s.length() <= 1)
            return true;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else
                return false;
        }
        return true;
    }
    // 暴力
    public static String shortestPalindrome(String s) {
        int len = s.length();
        if (len <= 1)
            return s;
        if (len == 2)
            return s.charAt(1) == s.charAt(0) ? s : s.charAt(1) + s;
        for (int i = len / 2 - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(i+2)) {
                int l = i, r = i + 2;
                while (l >= 0 && r < len) {
                    if (s.charAt(l) == s.charAt(r)) {
                        l--;
                        r++;
                    } else
                        break;
                }
                if (l == -1) {
                    return new StringBuffer(s.substring(r, len)).reverse().toString() + s;
                }
            } if (s.charAt(i) == s.charAt(i+1)) {
                int l = i, r = i+1;
                while (l >= 0 && r < len) {
                    if (s.charAt(l) == s.charAt(r)) {
                        l--;
                        r++;
                    } else
                        break;
                }
                if (l == -1)
                    return new StringBuffer(s.substring(r, len)).reverse().toString() + s;
            }
        }
        return new StringBuffer(s.substring(1)).reverse().toString() + s;
    }
    // KMP算法, 通过将s和s的逆序拼接, 再根据next数组获取结果, 如果next数组最后一位值为s长度-1, 则为回文串, 否则加上最后一位值对应s中字符之后字串的逆序
    public static String shortestPalindrome2(String s) {
        int len = s.length();
        if (len <= 1)
            return s;
        String str = s + "#" + new StringBuffer(s).reverse().toString();
        int strLen = str.length();
        int[] next = new int[strLen];
        next[0] = -1;
        for (int i = 1; i < strLen; i++) {
            int a = next[i-1];
            while (a != -1) {
                if (str.charAt(i-1) == str.charAt(a)) {
                    next[i] = a + 1;
                    break;
                } else
                    a = next[a];
            }
            if (a == -1)
                next[i] = 0;
        }
        for (int i : next) {
            System.out.print(i + " ");
        }
        // aacecaaa#aaacecaa
        System.out.println();
        if (next[strLen-1] == len-1)
            return s;
        else
            return new StringBuffer(s.substring(next[strLen-1]+1)).reverse().toString() + s;
    }
}
