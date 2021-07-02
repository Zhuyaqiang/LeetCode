package other;

import java.util.*;

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
        System.out.println(rCalculate("(1+(4+5+2)-3)+(6+8)"));
//        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }
    public static int rCalculate(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 3);
        map.put('/', 3);
        map.put('(', 5);
        Deque<Object> queue = new LinkedList<>();
        Stack<Character> op = new Stack<>();
        int index = 0, len = s.length();
        int flag = 1;
        char[] chs = s.toCharArray();
        while (index < len) {
            char ch = chs[index];
            if (ch == ' ') {
                index++;
            } else  if (isDigit(ch)) {
                int num = ch - '0';
                int r = index + 1;
                while (r < len && isDigit(chs[r])) {
                    num = num * 10 + chs[r] - '0';
                    r++;
                }
                num = num * flag;
                flag = 1;
                index = r;
                queue.offer(num);
            } else {
                if (ch == '(' || op.isEmpty() || op.peek() == '(') {
                    op.push(ch);
                } else {
                    if (ch == ')') {
                        while (op.peek() != '(') {
                            queue.offer(op.pop());
                        }
                        op.pop();
                    } else {
                        while (!op.isEmpty() && op.peek() != '(' && map.get(op.peek()) >= map.get(ch)) {
                            queue.offer(op.pop());
                        }
                        op.push(ch);
                    }
                }
                index++;
            }
        }
        while (!op.isEmpty()) {
            queue.offer(op.pop());
        }
        Stack<Integer> res = new Stack<>();
        while (!queue.isEmpty()) {
            if (queue.peekFirst() instanceof Character) {
                char pop = (char)queue.pollFirst();
                int two = res.pop();
                int one = res.pop();
                int val;
                switch (pop) {
                    case '+':
                        val = one + two;
                        break;
                    case '-':
                        val = one - two;
                        break;
                    case '*':
                        val = one * two;
                        break;
                    default:
                        val = one / two;
                }
                res.push(val);
            } else {
                int pop = (int)queue.pollFirst();
                res.push(pop);
            }
        }
        return res.pop();
    }
    public static boolean isFu(char ch) {
        return ch == '+' || ch == '-';
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
