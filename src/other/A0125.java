package other;

/**
 * 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * 'A' 65
 * 'Z' 90
 * 'a' 97
 * 'z' 122
 * '0' 48
 * '9' 57
 */
public class A0125 {
    public static void main(String[] args) {
        String s = "race a car";
        boolean palindrome = isPalindrome(s);
        System.out.println(palindrome);
    }
    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length()-1;
        s = s.toLowerCase();
        while (l < r) {
            if (s.charAt(l) < 48 || (s.charAt(l) > 57 && s.charAt(l) < 97) || s.charAt(l) > 122) {
                l++;
                continue;
            }
            if (s.charAt(r) < 48 || (s.charAt(r) > 57 && s.charAt(r) < 97) || s.charAt(r) > 122) {
                r--;
                continue;
            }
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
}
