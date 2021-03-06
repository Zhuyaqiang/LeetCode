package other;

import java.util.Collections;
import java.util.Stack;

/**
 * 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 示例:
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 */
public class A0394 {
    public static void main(String[] args) {
        String s = "abc3[cd]xyz";
        System.out.println(rDecodeString(s));
    }

    public static String rDecodeString(String s) {
        Stack<String> stack = new Stack<>();
        int index = 0, len = s.length();
        char[] chs = s.toCharArray();
        while (index < len) {
            char ch = chs[index];
            if (isDigit(ch)) {
                int r = index + 1;
                while (r < len && isDigit(chs[r])) {
                    r++;
                }
                stack.push(s.substring(index, r));
                index = r;
            } else if (ch == '['){
                stack.push(ch + "");
                index++;
            } else if (ch == ']') {
                StringBuilder sb = new StringBuilder();
                while (!"[".equals(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                stack.pop();
                int count = Integer.parseInt(stack.pop());
                StringBuilder newSb = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    newSb.append(sb);
                }
                stack.push(newSb.toString());
                index++;
            } else {
                int r = index + 1;
                while (r < len && !isDigit(chs[r]) && chs[r] != '[' && chs[r] != ']') {
                    r++;
                }
                stack.push(s.substring(index, r));
                index = r;
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        return res.toString();
    }
    public static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static StringBuilder popAndAppend(Stack<Object> stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty() && stack.peek() instanceof String) {
            sb.append(stack.pop());
        }
        return sb;
    }

    public static String decodeString(String s) {
        char[] chs = s.toCharArray();
        Stack<Object> stack = new Stack<>();

        int num = 0;

        for (char ch : chs) {
            if (isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else if (ch == '[') {  // 数字入栈
                stack.push(num);
                num = 0;
            } else if (ch == ']') {
                // 字符串出栈, 并且计算次数
                String str = popAndGetString(stack);
                int times = (int) stack.pop();
                String temp = String.join("", Collections.nCopies(times, str));
                stack.push(temp);
            } else { // 字符, 入栈
                stack.push(String.valueOf(ch));
            }
        }
        return reverse(popAndGetString(stack));
    }


    public static String popAndGetString(Stack<Object> stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.empty() && stack.peek() instanceof String) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static String reverse(String str) {
        char[] res = str.toCharArray();
        int l = 0, r = res.length - 1;
        while (l < r) {
            char temp = res[l];
            res[l] = res[r];
            res[r] = temp;
            l++;
            r--;
        }
        return String.valueOf(res);
    }
}
