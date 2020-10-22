package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * 示例 1：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 提示：
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 */
public class A0763 {
    public static void main(String[] args) {
        System.out.println(partitionLabels("caedbdedda"));
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }
    public static List<Integer> partitionLabels(String S) {
        int len = S.length();
        if (len <= 1)
            return Arrays.asList(len);
        List<Integer> res = new ArrayList<>();
        int[] front = new int[26];
        Arrays.fill(front, -1);
        int[] back = new int[26];
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (front[ch - 'a'] == -1)
                front[ch - 'a'] = i;
            back[ch - 'a'] = i;
        }
        int index = 0, start = 0, end = 0;
        while (index < S.length()) {
            start = front[S.charAt(index) - 'a'];
            end = back[S.charAt(index) - 'a'];
            int temp = start + 1;
            while (temp <= end) {
                end = Math.max(end, back[S.charAt(temp) - 'a']);
                temp ++;
            }
            res.add(temp - start);
            index = temp;
        }
        return res;
    }
}
