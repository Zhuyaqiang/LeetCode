package str;

/**
 * 反转字符串中的元音字母
 *
 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 示例 1：
 输入："hello"
 输出："holle"
 示例 2：
 输入："leetcode"
 输出："leotcede"

 提示：
 元音字母不包含字母 "y" 。
 */
public class A0345 {
    public static void main(String[] args) {
        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));
        System.out.println(reverseVowels("aA"));
    }
    public static String reverseVowels(String s) {
        StringBuilder sb = new StringBuilder(s);
        int len = sb.length();
        int l = 0, r = len - 1;
        while (l < r) {
            while (l < r && !check(sb.charAt(l))) l++;
            while (l < r && !check(sb.charAt(r))) r--;
            if (l < r) {
                char temp = sb.charAt(l);
                sb.replace(l, l+1, sb.charAt(r) + "");
                sb.replace(r, r+1, temp + "");
                l++;
                r--;
            }
        }
        return sb.toString();
    }
    public static boolean check(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
