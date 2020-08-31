package other;

/**
 * 重复的子字符串
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class A0459 {
    // 枚举
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for (int i = 1; 2 * i <= len; i++) {
            if (len % i == 0) {
                boolean res = true;
                for (int j = i; j < len; j ++) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        res = false;
                        break;
                    }
                }
                if (res)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern2("abc12345678abc"));
    }
    // kmp, 最长公共前后缀
    // https://leetcode-cn.com/problems/repeated-substring-pattern/solution/459-zhong-fu-de-zi-zi-fu-chuan-kmpjing-dian-wen-ti/
    public static boolean repeatedSubstringPattern2(String s) {
        int len = s.length();
        if (len <= 1)
            return false;
        int[] next = new int[len];
        next[0] = -1;
        for (int i = 1; i < len; i++) {
            int a = next[i - 1];
            while (a != -1) {
                if (s.charAt(i-1) == s.charAt(a)) {
                    next[i] = a + 1;
                    break;
                } else {
                    a = next[a];
                }
            }
            if (a == -1)
                next[i] = 0;
        }
        if (s.charAt(len-1) != s.charAt(next[len-1]))
            return false;
        int res = next[len-1] + 1;
        if (len % (len - res) == 0)
            return true;
        return false;
    }
}
