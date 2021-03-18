package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 基本计算器
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 *
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 *
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 */
public class A0224 {
    public static void main(String[] args) {
//        System.out.println(calculate("1 + 1"));
        System.out.println(calculate(" 2-1 + 2 "));
//        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }
    public static int calculate(String s) {
        s = s.replace(" ", "");
        Stack<Character> op = new Stack<>();
        List<String> num = new ArrayList<>();
        int index = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (isDigit(ch)) {
                int end = index + 1;
                while (end < s.length() && isDigit(s.charAt(end))) {
                    end++;
                }
                num.add(s.substring(index, end));
                index = end;
            } else {
                if (ch == ')') {
                    while (op.peek() != '(') {
                        num.add(op.pop() + "");
                    }
                    op.pop();
                } else if (ch == '(') {
                    op.push(ch);
                } else {
                    if (!op.isEmpty() && op.peek() == '(') {
                        op.push(ch);
                    } else {
                        num.add(ch + "");
                    }
                }
                index++;
            }
        }
        while (!op.isEmpty()) {
            num.add(op.pop() + "");
        }
        Stack<Integer> nums = new Stack<>();
        for (String str : num) {
            if (str.equals("+") || str.equals("-")) {
                int two = nums.pop();
                int one = nums.pop();
                int val = str.equals("+") ? one + two : one - two;
                nums.push(val);
            } else {
                nums.push(Integer.parseInt(str));
            }
        }
        return nums.pop();
    }
    public static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
