package str;

/**
 * 有效数字
 * 验证给定的字符串是否可以解释为十进制数字。
 * 例如:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 * 更新于 2015-02-10:
 * C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码。
 */
public class A0065 {
    public static void main(String[] args) {
        System.out.println(isNumber("3.")); // true
        System.out.println(isNumber("4e+")); // false
        System.out.println(isNumber("+.8")); // true
        System.out.println(isNumber("-.")); // false
        System.out.println(isNumber("-")); // false
        System.out.println(isNumber("0..")); // false
    }
    // 先根据e字符拆分, 分别判断是前部分还是后部分, 后部分直接判断数字, 前部分再根据.拆分, 再判断数字
    public static boolean isNumber(String s) {
        s = s.trim();
        int len = s.length();
        if (len == 0)
            return false;
        int i;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'e')
                break;
        }
        if (i == 0)
            return false;
        else if (i == len-1)
            return false;
        else if (i == len)
            return checkFront(s);
        else
            return checkFront(s.substring(0, i)) && checkBack(s.substring(i+1, len));
    }

    public static boolean checkFront(String s) {
        boolean dotFlag = false;
        if ((s.charAt(0) == '+' || s.charAt(0) == '-') && s.length() > 1)
            s = s.substring(1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                if (dotFlag)
                    return false;
                dotFlag = true;
            }
        }
        String[] splits = s.split("\\.");
        if (splits.length == 0)
            return false;
        else if (splits.length > 2)
            return false;
        else {
            for (int i = 0; i < splits.length; i++) {
                for (int j = 0; j < splits[i].length(); j++) {
                    char c = splits[i].charAt(j);
                    if (c > '9' || c < '0')
                        return false;
                }
            }
        }
        return true;
    }
    public static boolean checkBack(String s) {
        if ((s.charAt(0) == '+' || s.charAt(0) == '-') && s.length() > 1)
            s = s.substring(1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > '9' || c < '0')
                return false;
        }
        return true;
    }
}
