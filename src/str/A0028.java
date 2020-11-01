package str;

import java.util.Arrays;

/**
 * 实现strStr()
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class A0028 {
    public static void main(String[] args) {
        System.out.println(strStr2("hello", "ll"));
        System.out.println(strStr2("aaaaa", "bba"));
    }
    // 暴力
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        int hLen = haystack.length(), nLen = needle.length();
        if (nLen > hLen)
            return -1;
        for (int i = 0; i <= hLen - nLen; i++) {
            boolean flag = false;
            for (int j = 0; j < nLen; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                return i;
        }
        return -1;
    }
    // kmp
    public static int strStr2(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        int hLen = haystack.length(), nLen = needle.length();
        if (nLen > hLen)
            return -1;
        int[] next = new int[nLen];
        next[0] = -1;
        for (int i = 1; i < nLen; i++) {
            int a = next[i-1];
            while (a != -1) {
                if (needle.charAt(a) == needle.charAt(i-1)) {
                    next[i] = a+1;
                    break;
                } else {
                    a = next[a];
                }
            }
            if (a == -1)
                next[i] = 0;
        }
        int hIndex = 0;
        while (hIndex <= hLen - nLen) {
            int i;
            for (i = 0; i < nLen; i++) {
                if (haystack.charAt(hIndex + i) != needle.charAt(i)) {
                    hIndex += (i - next[i]);
                    break;
                }
            }
            if (i == nLen)
                return hIndex;
        }
        return -1;
    }
}
