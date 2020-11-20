package str;

import java.util.*;

/**
 * 基本计算器2
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
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
public class A0227 {
    public static void main(String[] args) {
        System.out.println(calculate2("3+2*2"));
        System.out.println(calculate2(" 3/2 "));
        System.out.println(calculate2(" 3+5 / 2 "));
        System.out.println(calculate2("1-1+1"));
        System.out.println(calculate2("1*2-3/4+5*6-7*8+9/10"));
    }

    // 中缀表达式转后缀表达式, 通过栈计算
    public static int calculate2(String s) {
        Deque<String> deque = new ArrayDeque<>();
        Stack<Character> op = new Stack<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('(', 9);
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 5);
        map.put('/', 5);
        int index = 0;
        int len = s.length();
        while (index < s.length()) {
            char c = s.charAt(index);
            if (c == ' ') {
                index ++;
                continue;
            }
            else if (c == '(')
                op.push(c);
            else if (c == '*' || c == '/' || c == '+' || c == '-') {
                if (op.isEmpty())
                    op.push(c);
                else {
                    char pre = op.peek();
                    while (map.get(pre) >= map.get(c)) {
                        deque.offer(pre + "");
                        op.pop();
                        if (op.isEmpty()) {
                            break;
                        }
                        pre = op.peek();
                    }
                    op.push(c);
                }
            } else if (c == ')') {
                char pop = op.pop();
                while (pop != '(') {
                    deque.offer(pop + "");
                    pop = op.pop();
                }
            } else {
                int val = 0;
                while (index < len && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                    val = val * 10 + (s.charAt(index) - '0');
                    index ++;
                }
                deque.offer(val + "");
                index --;
            }
            index ++;
        }
        while (!op.isEmpty())
            deque.offer(op.pop() + "");
        int n = deque.size();
        Stack<Integer> num = new Stack<>();
        while (!deque.isEmpty()) {
            String poll = deque.poll();
            if (poll.equals("+") || poll.equals("-") || poll.equals("*") || poll.equals("/")) {
                int twoNum, oneNum;
                twoNum = num.pop();
                oneNum = num.pop();
                if (poll.equals("+"))
                    num.push(oneNum + twoNum);
                else if (poll.equals("-"))
                    num.push(oneNum - twoNum);
                else if (poll.equals("*"))
                    num.push(oneNum * twoNum);
                else
                    num.push(oneNum / twoNum);

            } else {
                num.push(getVal(poll));
            }
        }
        return num.pop();
    }
    public static int calculate(String s) {
        Stack<Integer> num = new Stack<>();
        Stack<Character> op = new Stack<>();
        int index = 0;
        int len = s.length();
        op.push('+');
        while (index < len) {
            char c = s.charAt(index);
            if (c == ' ') {
                index ++;
                continue;
            } else if (c == '+' || c == '-') {
                op.push(c);
            } else if (c == '*' || c == '/') {
                int tempIndex1 = index + 1;
                while (tempIndex1 < len && s.charAt(tempIndex1) == ' ')  {
                    tempIndex1 ++;
                }
                int tempIndex2 = tempIndex1 + 1;
                while (tempIndex2 < len && s.charAt(tempIndex2) >= '0' && s.charAt(tempIndex2) <= '9')
                    tempIndex2 ++;
                String numStr = s.substring(tempIndex1, tempIndex2);
                int twoNum = getVal(numStr);
                int oneNum = num.pop();
                if (c == '*')
                    num.push(oneNum * twoNum);
                else
                    num.push(oneNum / twoNum);
                index = tempIndex2 - 1;
            } else {
                char operate = op.pop();
                int tempIndex = index + 1;
                while (tempIndex < len && s.charAt(tempIndex) >= '0' && s.charAt(tempIndex) <= '9')
                    tempIndex ++;
                String numStr = s.substring(index, tempIndex);
                int val = getVal(numStr);
                if (operate == '+')
                    num.push(val);
                else
                    num.push(-val);
                index = tempIndex - 1;
            }
            index ++;
        }
        int res = 0;
        while (!num.isEmpty())
            res += num.pop();
        return res;
    }
    public static int getVal(String numStr) {
        int val = 0;
        for (int i = 0; i < numStr.length(); i++) {
            val = val * 10 + (numStr.charAt(i) - '0');
        }
        return val;
    }
}
