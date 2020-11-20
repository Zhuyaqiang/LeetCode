package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 计数二进制子串
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * 重复出现的子串要计算它们出现的次数。
 * 示例 1 :
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * 注意：
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 */
public class A0696 {
    public static void main(String[] args) {
        System.out.println(countBinarySubstrings2("00110011"));
    }
    // 暴力法, 超时
    public static int countBinarySubstrings(String s) {
        if(s.length() < 2)
            return 0;
        int ans = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int one = 0, count = 0;
            if (s.charAt(i) =='1')
                one ++;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) != s.charAt(j-1))
                    count ++;
                if (count >= 2)
                    break;
                if (s.charAt(j) == '1')
                    one ++;
                if (one * 2 == j-i+1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int countBinarySubstrings2(String s) {
        int len = s.length();
        List<Integer> list = new ArrayList<>();
        if (len < 2)
            return 0;
        int index = 1;
        int count = 1;
        while (index < len) {
            if (s.charAt(index) == s.charAt(index-1)) {
                index ++;
                count ++;
            } else {
                list.add(count);
                count = 1;
                index ++;
            }
        }
        list.add(count);
        int ans = 0;
        for (int i = 1; i < list.size(); i++) {
            ans += Math.min(list.get(i), list.get(i-1));
        }
        return ans;
    }
}
