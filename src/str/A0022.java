package str;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例：
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 */
public class A0022 {
    public static void main(String[] args) {
        System.out.println(rGenerateParenthesis(3));
    }
    public static List<String> rGenerateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>(Collections.singleton(""));
        }
        List<String> res = new ArrayList<>();
        rBacktrack(n, res, new StringBuilder(), 0, 0);
        return res;
    }
    public static void rBacktrack(int n, List<String> res, StringBuilder sb, int l, int r) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        if (l < n) {
            rBacktrack(n, res, sb.append('('), l + 1, r);
            sb.delete(sb.length() - 1, sb.length());
        }
        if (r < l) {
            rBacktrack(n, res, sb.append(')'), l, r + 1);
            sb.delete(sb.length() - 1, sb.length());
        }
    }
        // 暴力法
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", n);
        return res;
    }
    public static void backtrack(List<String> res, String curr, int n) {
        if (curr.length() == n * 2) {
            if (check(curr))
                res.add(curr);
            return;
        }
        backtrack(res, curr + "(", n);
        backtrack(res, curr + ")", n);
    }
    public static boolean check(String str) {
        int n = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                n++;
            else
                n--;
            if (n < 0)
                return false;
        }
        return n == 0;
    }
    // 剪枝
    public static List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        backtrack2(res, "", n, 0, 0);
        return res;
    }
    public static void backtrack2(List<String> res, String str, int n, int l, int r) {
        if (str.length() == 2 * n) {
            res.add(str);
            return;
        }
        if (l < n)
            backtrack2(res, str + "(", n, l + 1, r);
        // 左括号大于右括号数量
        if (r < l)
            backtrack2(res, str + ")", n, l, r + 1);
    }
}
