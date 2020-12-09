package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 将数组拆分成非波那契序列
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 * 示例 1：
 * 输入："123456579"
 * 输出：[123,456,579]
 * 示例 2：
 * 输入: "11235813"
 * 输出: [1,1,2,3,5,8,13]
 * 示例 3：
 * 输入: "112358130"
 * 输出: []
 * 解释: 这项任务无法完成。
 * 示例 4：
 * 输入："0123"
 * 输出：[]
 * 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
 * 示例 5：
 * 输入: "1101111"
 * 输出: [110, 1, 111]
 * 解释: 输出 [11,0,11,11] 也同样被接受。
 * 提示：
 * 1 <= S.length <= 200
 * 字符串 S 中只含有数字。
 */
public class A0842 {
    public static void main(String[] args) {
        System.out.println(splitIntoFibonacci("123456579"));
        res = new ArrayList<>();
        flag = false;
        System.out.println(splitIntoFibonacci("11235813"));
        res = new ArrayList<>();
        flag = false;
        System.out.println(splitIntoFibonacci("112358130"));
        res = new ArrayList<>();
        flag = false;
        System.out.println(splitIntoFibonacci("0123"));
        res = new ArrayList<>();
        flag = false;
        System.out.println(splitIntoFibonacci("1101111"));
        res = new ArrayList<>();
        flag = false;
        System.out.println(splitIntoFibonacci("5511816597"));
    }

    static List<Integer> res = new ArrayList<>();
    static boolean flag = false;

    public static List<Integer> splitIntoFibonacci(String S) {
        backtrack(new ArrayList<>(), S, 0, 1);
        return res;
    }

    public static void backtrack(List<Integer> list, String s, int l, int r) {
        if (flag)
            return;
        if (l >= s.length() && list.size() >= 3) {
            res = new ArrayList<>(list);
            flag = true;
            return;
        }
        if (r >= s.length() + 1)
            return;
//        if (s.charAt(l) == '0')
//            return;
        if (r >= l + 2 && s.charAt(l) == '0')
            return;
        int size = list.size();
        if (size >= 2) {
            int one = list.get(size - 1);
            int two = list.get(size - 2);
            try {
                int val = Integer.parseInt(s.substring(l, r));
                if (val == one + two) {
                    list.add(val);
                    backtrack(list, s, r, r + 1);
                    list.remove(list.size() - 1);
                } else {
                    backtrack(list, s, l, r + 1);
                }
            } catch (NumberFormatException e) {

            }
        } else {
            for (int i = l + 1; i <= s.length(); i++) {
                if (i > l + 1 && s.charAt(l) == '0')
                    continue;
                try {
                    int val = Integer.parseInt(s.substring(l, i));
                    list.add(val);
                    backtrack(list, s, i, i + 1);
                    list.remove(list.size() - 1);
                } catch (NumberFormatException e) {
                }
            }
        }
    }
}
