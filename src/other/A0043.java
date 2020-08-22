package other;

/**
 * 字符串相乘
 */
public class A0043 {
    public static void main(String[] args) {
        System.out.println(multiply("9133", "0"));
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
