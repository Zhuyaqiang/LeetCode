package other;

/**
 * 最长公共前缀
 *
 编写一个函数来查找字符串数组中的最长公共前缀。
 如果不存在公共前缀，返回空字符串 ""。
 示例 1:
 输入: ["flower","flow","flight"]
 输出: "fl"
 示例 2:
 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。
 说明:
 所有输入只包含小写字母 a-z 。
 */
public class A0014 {
    public static void main(String[] args) {
        String[] s = {"dog","racecar","car"};
        System.out.println(rLongestCommonPrefix(s));
    }


    public static String rLongestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        int index = 0, minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(str.length(), minLen);
        }
        while (index < minLen) {
            char ch = strs[0].charAt(index);
            boolean flag = false;
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(index) != ch) {
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
            index ++;
        }
        return strs[0].substring(0, index);
    }



    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        int index = 0;
        boolean flag = true;
        while (flag) {
            if (index == strs[0].length())
                break;
            char temp = strs[0].charAt(index);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() == index || strs[i].charAt(index) != temp) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                index++;
            }
        }
        return index == 0 ? "" : strs[0].substring(0, index);
    }

    public static String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0)
            return "";
        String ans = strs[0];
        for (int i = 0; i < strs.length; i++) {
            int index = 0;
            while (index < ans.length() && index < strs[i].length() && ans.charAt(index) == strs[i].charAt(index))
                index ++;
            ans = ans.substring(0, index);
            if (ans.length() == 0)
                 break;
        }
        return ans;
    }
}
