package other;

/**
 * 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 */
public class A0043 {
    public static void main(String[] args) {
//        System.out.println(multiply("9133", "0"));
//        System.out.println(multiply("2", "3"));
//        System.out.println(multiply("123", "456"));
//        System.out.println(multiply("7967", "7067"));
    }

    public static String rMultiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if (len1 < len2) {
            String sTemp = num1;
            num1 = num2;
            num2 = sTemp;
            len1 = num1.length();
            len2 = num2.length();
        }
        String res = null;
        String add = "0";
        for (int i = len2 - 1; i >= 0; i--) {
            char ch = num2.charAt(i);
            String mulRes = strMul(num1, ch);
            if (res == null) {
                res = mulRes;
            } else {
                if (!mulRes.equals("0")) {
                    res = strAdd(res, mulRes + add);
                }
                add = add + "0";
            }
        }
        return res;
    }
    public static String strAdd(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int index2 = num2.length() - 1, index1 = num1.length() - 1;
        int c = 0;
        while (index1 >= 0) {
            int val = num1.charAt(index1) - '0' + num2.charAt(index2) - '0' + c;
            c = val / 10;
            index1--;
            index2--;
            res.insert(0, (val % 10) + "");
        }
        while (index2 >= 0) {
            int val = num2.charAt(index2) - '0' + c;
            c = val / 10;
            index2--;
            res.insert(0, (val % 10) + "");
        }
        if (c > 0) {
            res.insert(0, "1");
        }
        return res.toString();
    }
    public static String strMul(String num1, char ch) {
        int c = 0;
        int num = ch - '0';
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            int val = num * (num1.charAt(i) - '0') + c;
            c = val / 10;
            val = val % 10;
            sb.insert(0, val + "");
        }
        if (c != 0) {
            sb.insert(0, c + "");
        }
        int index = 0;
        while (index < sb.length() - 1 && sb.charAt(index) == '0') {
            index++;
        }
        return sb.substring(index);
    }





    public static String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        if (num1.length() > num2.length()) {
            String temp = new String(num1);
            num1 = num2;
            num2 = temp;
        }
        // num2长
        int len1 = num1.length(), len2 = num2.length();
        int index1 = len1 - 1;
        String res = "0";
        StringBuilder add = new StringBuilder();
        while (index1 >= 0) {
            String ans = singleMultiply(num2, num1.charAt(index1));
            ans = ans + add;
            res = add(res, ans);
            add.append("0");
            index1 --;
        }
        return res;
    }
    public static String add(String a, String b) {
        if (a.length() > b.length()) {
            String temp = new String(a);
            a = b;
            b = temp;
        }
        // b 长
        int aIndex = a.length() - 1, bIndex = b.length() - 1, c = 0;
        StringBuilder res = new StringBuilder();
        while (aIndex >= 0) {
            int aVal = a.charAt(aIndex) - '0';
            int bVal = b.charAt(bIndex) - '0';
            int val = aVal + bVal + c;
            c = val / 10;
            res.insert(0, (char) (val % 10 + '0'));
            aIndex --;
            bIndex --;
        }
        while (bIndex >= 0) {
            int bVal = b.charAt(bIndex) - '0';
            int val = bVal + c;
            c = val / 10;
            res.insert(0, (char) (val % 10 + '0'));
            bIndex --;
        }
        if (c > 0)
            res.insert(0, "1");
        return res.toString();
    }
    public static String singleMultiply(String nums, char ch) {
        StringBuilder res = new StringBuilder();
        int c = 0;
        for (int i = nums.length() - 1; i >= 0; i--) {
            int val1 = ch - '0';
            int val2 = nums.charAt(i) - '0';
            int val = val1 * val2 + c;
            c = val / 10;
            res.insert(0, (char) (val % 10 + '0'));
        }
        if (c > 0)
            res.insert(0, (char)(c + '0'));
        return res.toString();
    }
}
