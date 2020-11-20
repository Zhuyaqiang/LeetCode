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
        System.out.println(rMultiply("9133", "0"));
        System.out.println(rMultiply("2", "3"));
        System.out.println(rMultiply("123", "456"));
    }
    public static String rMultiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        if (num1.length() > num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        // nums1短
        int len1 = num1.length(), len2 = num2.length();
        StringBuilder sb = new StringBuilder(), add = new StringBuilder(), res = new StringBuilder("0");
        for (int i = len2 - 1; i >= 0; i --) {
            StringBuilder mulRes = rSingleMul(num1, num2.charAt(i));
            mulRes.append(add);
            add.append("0");
            res = rAdd(res, mulRes);
        }
        return res.toString();
    }
    public static StringBuilder rAdd(StringBuilder res, StringBuilder add) {
        // res长
        if (res.length() < add.length()) {
            StringBuilder temp = res;
            res = add;
            add = temp;
        }
        int c = 0, rIndex = res.length() - 1, aIndex = add.length() - 1;
        while (aIndex >= 0) {
            int val = (res.charAt(rIndex) - '0') + (add.charAt(aIndex) - '0') + c;
            c = val / 10;
            val = val % 10;
            res.replace(rIndex, rIndex + 1, val + "");
            rIndex --;
            aIndex --;
        }
        while (rIndex >= 0) {
            int val = res.charAt(rIndex) - '0' + c;
            c = val / 10;
            val = val % 10;
            res.replace(rIndex, rIndex + 1, val + "");
            rIndex --;
        }
        if (c > 0)
            res.insert(0, c + "");
        return res;
    }
    public static StringBuilder rSingleMul(String str, char ch) {
        int c = 0;
        StringBuilder res = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i --) {
            int val = (str.charAt(i) - '0') * (ch - '0') + c;
            c = val / 10;
            val = val % 10;
            res.insert(0, val + "");
        }
        if (c > 0)
            res.insert(0, c + "");
        return res;
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
