package other;

/**
 * 有效数字
 * 有效数字（按顺序）可以分成以下几个部分：
 *
 *     一个 小数 或者 整数
 *     （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 *
 * 小数（按顺序）可以分成以下几个部分：
 *
 *     （可选）一个符号字符（'+' 或 '-'）
 *     下述格式之一：
 *         至少一位数字，后面跟着一个点 '.'
 *         至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 *         一个点 '.' ，后面跟着至少一位数字
 *
 * 整数（按顺序）可以分成以下几个部分：
 *
 *     （可选）一个符号字符（'+' 或 '-'）
 *     至少一位数字
 *
 * 部分有效数字列举如下：
 *
 *     ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 *
 * 部分无效数字列举如下：
 *
 *     ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 *
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "0"
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：s = "e"
 * 输出：false
 * 示例 3：
 * 输入：s = "."
 * 输出：false
 * 示例 4：
 * 输入：s = ".1"
 * 输出：true
 */
public class A0065 {
    public static void main(String[] args) {
        String[] trueDigit = {"2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789", "0", ".1"};
        for (String s : trueDigit) {
            System.out.println(isNumber(s) + ", " + s);
        }
        System.out.println("---------");
        String[] falseDigit = {"abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53", ".", "e", ".e1", "0..", ".+"};
        for (String s : falseDigit) {
            System.out.println(isNumber(s) + ", " + s);
        }
    }
//    public static void main(String[] args) {
//        System.out.println(isNumber("+."));
//    }
    public static boolean isNumber(String s) {
        char[] chs = s.toCharArray();
        boolean isNum = false, isE = false, isFloat = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = chs[i];
            // +  -只能放首位或者e后面
            if ((ch == '+' || ch == '-') && (i == 0 || chs[i - 1] == 'e' || chs[i - 1] == 'E')) {
            } else if ((ch == 'e' || ch == 'E') && !isE && isNum) {
                // e只能出现一次且前面和后面必须有数字
                isE = true;
                isNum = false;
            } else if (ch == '.' && !isFloat && !isE) {
                // .只能出现一次且不能在e后面
                isFloat = true;
            } else if (isDigit(ch)) {
                isNum = true;
            } else {
                return false;
            }
        }
        return isNum;
    }
    public static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
