package other;

import java.util.Stack;

/**
 * 反转每对括号间的子串
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * 注意，您的结果中 不应 包含任何括号。
 * 示例 1：
 * 输入：s = "(abcd)"
 * 输出："dcba"
 * 示例 2：
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 * 示例 3：
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 * 示例 4：
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 * 提示：
 * 0 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 我们确保所有括号都是成对出现的
 */
public class A1190 {
    public static void main(String[] args) {
        System.out.println(reverseParentheses("(abcd)"));
        System.out.println(reverseParentheses("(u(love)i)"));
        System.out.println(reverseParentheses("(ed(et(oc))el)"));
        System.out.println(reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }
    public static String reverseParentheses2(String s) {
        int len = s.length();
        int[] pair = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int index = stack.pop();
                pair[i] = index;
                pair[index] = i;
            }
        }
        int step = 1, index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < len) {
            if (s.charAt(index) == '(' || s.charAt(index) == ')') {
                index = pair[index];
                step = -step;
            } else {
                sb.append(s.charAt(index));
            }
            index += step;
        }
        return sb.toString();
    }
    public static String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        int len = s.length();
        int start = 0, end = 0;
        StringBuilder left = new StringBuilder("(");
        while (end < len) {
            while (end < len && s.charAt(end) >= 'a' && s.charAt(end) <= 'z') {
                end++;
            }
            StringBuilder sb = new StringBuilder(s.substring(start, end));
            if (end == len) {
                stack.push(sb);
                break;
            }
            if (s.charAt(end) == '(') {
                stack.push(sb);
                stack.push(left);
            } else {
                while (!stack.isEmpty() && !stack.peek().equals(left)) {
                    StringBuilder pop = stack.pop();
                    sb = pop.append(sb);
                }
                stack.pop();
                sb = sb.reverse();
                stack.push(sb);
            }
            end++;
            start = end;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            StringBuilder pop = stack.pop();
            res = pop.append(res);
        }
        return res.toString();
    }
}
