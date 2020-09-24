package str;

import java.util.Stack;

/**
 * 面试题 16.26. 计算器
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class M1626 {
    public static void main(String[] args) {
        System.out.println(calculate("2+2"));
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate("3/2"));
        System.out.println(calculate("3+5/2"));
    }
    public static int calculate(String s) {
        Stack<Integer> num = new Stack<>();
        Stack<Character> cal = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;
            if (c >= '0' && c <= '9') {
                num.push(c-'0');
            } else {
                if (c == '+' || c == '-')
                    cal.push(c);
                else {
                    i++;
                    int b = s.charAt(i) - '0';
                    int a = num.pop();
                    if (c == '*') {
                        num.push(a * b);
                    } else {
                        num.push(a / b);
                    }
                }
            }
        }
        while (!cal.isEmpty()) {
            int a = num.pop();
            int b = num.pop();
            if (cal.pop() == '+') {
                num.push(a + b);
            } else
                num.push(b - a);
        }
        return num.pop();
    }
}
