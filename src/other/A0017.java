package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 **/
public class A0017 {
    public static void main(String[] args) {
        List<String> strings = letterCombinations("23");
        System.out.println(strings);
    }
    public static List<String> res = new ArrayList<>();
    public static char[][] board= {{'a','b','c'}, {'d','e','f'}, {'g','h','i'}, {'j','k','l'}, {'m','n','o'}, {'p','q','r','s'}, {'t','u','v'}, {'w','x','y','z'}};
    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return res;
        backtrack(digits, 0, "");
        return res;
    }
    public static void backtrack(String digits, int index, String ans) {
        if (index == digits.length()) {
            res.add(ans);
            return;
        }
        int val = digits.charAt(index) - '0' - 2;
        for (int i = 0; i < board[val].length; i++) {
            backtrack(digits, index +1, ans + board[val][i]);
        }
    }
}
