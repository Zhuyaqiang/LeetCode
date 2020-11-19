package other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 移掉K位数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class A0402 {
    public static void main(String[] args) {
        System.out.println(removeKdigits3("1432219", 3));
        System.out.println(removeKdigits3("10200", 1));
        System.out.println(removeKdigits3("10", 2));
        System.out.println(removeKdigits3("0", 0));
    }
    // 递归, 超时
    public static int res = Integer.MAX_VALUE;
    public static String removeKdigits(String num, int k) {
        if (k == num.length())
            return "0";
        if (k == 0)
            return num;
        boolean[] removed = new boolean[num.length()];
        for (int i = 0; i < num.length(); i++)
            backtrack(num, i, k, 0, removed);
        return res + "";
    }

    public static void backtrack(String num, int index, int k, int curr, boolean[] removed) {
        if (curr >= k)
            return;
        removed[index] = true;
        curr++;
        if (curr == k) {
            int ans = 0;
            for (int i = 0; i < num.length(); i++) {
                if (!removed[i])
                    ans = ans * 10 + num.charAt(i) - '0';
            }
            res = Math.min(ans, res);
            removed[index] = false;
            return;
        }
        for (int i = 0; i < num.length(); i++) {
            if (!removed[i])
                backtrack(num, i, k, curr, removed);
        }
        removed[index] = false;
    }
    // 贪心
    // 从左往右遍历, 当i <　i-1时, 删除i-1可以获得当前最小的, 如果没有则删除最后一个字符
    public static String removeKdigits2(String num, int k) {
        int len = num.length();
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < k; i++) {
            boolean flag = false;
            for (int j = 1; j < sb.length(); j++) {
                if (sb.charAt(j) < sb.charAt(j-1)) {
                    flag = true;
                    sb.delete(j-1, j);
                    break;
                }
            }
            if (!flag)
                sb.delete(sb.length() - 1, sb.length());
        }
        if (sb.length() == 0)
            return "0";
        else {
            int index = 0;
            while (index < sb.length() && sb.charAt(index) == '0')
                index ++;
            return index == sb.length() ? "0" : sb.substring(index, sb.length());
        }
    }

    // 贪心 + 单调非递减栈
    public static String removeKdigits3(String num, int k) {
         Stack<Character> stack = new Stack<>();
         int len = num.length();
         for (int i = 0; i < len; i++) {
             char ch = num.charAt(i);
             while (!stack.isEmpty() && k > 0 && ch < stack.peek()) {
                 stack.pop();
                 k--;
             }
             stack.push(ch);
         }
         for (int i = k; i > 0; i--)
             stack.pop();
         StringBuilder sb = new StringBuilder();
         boolean flag = false;
         Stack<Character> stack2 = new Stack<>();
         while (!stack.isEmpty())
             stack2.push(stack.pop());
         while (!stack2.isEmpty()) {
             Character ch = stack2.pop();
             if (ch == '0' && !flag)
                 continue;
             flag = true;
             sb.append(ch);
         }
         return sb.length() == 0 ? "0" : sb.toString();
    }

}
