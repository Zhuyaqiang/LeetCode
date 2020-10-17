package str;

/**
 * 整数转换英文表示
 * 将非负整数转换为其对应的英文表示。可以保证给定输入小于 231 - 1 。
 * 示例 1:
 * 输入: 123
 * 输出: "One Hundred Twenty Three"
 * 示例 2:
 * 输入: 12345
 * 输出: "Twelve Thousand Three Hundred Forty Five"
 * 示例 3:
 * 输入: 1234567
 * 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4:
 * 输入: 1234567891
 * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class A0273 {
    public static void main(String[] args) {
        System.out.println(numberToWords(123));
        System.out.println(numberToWords(12345));
        System.out.println(numberToWords(1234567));
        System.out.println(numberToWords(1234567891));
        System.out.println(numberToWords(101));
        System.out.println(numberToWords(1000000));
    }
    public static String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        String[] convert = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] convert2 = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] convert3 = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 4 && num > 0; i++) {
            StringBuilder sb = new StringBuilder();
            int val;

            val = num % 10;
            int pre = val;
            num = num / 10;
            if (val != 0) {
                sb.insert(0, " " + convert[val]);
            }

            if (num != 0) {
                val = num % 10;
                num = num / 10;
                if (val > 1) {
                    sb.insert(0, " " + convert2[val]);
                } else if (val == 1) {
                    sb.delete(0, sb.length());
                    sb.insert(0, " " + convert3[pre]);
                }
            }

            if (num != 0) {
                val = num % 10;
                num = num / 10;
                if (val != 0) {
                    sb.insert(0, " " + convert[val] + " Hundred");
                }
            }
            if (i == 1 && sb.length() != 0)
                sb.append(" Thousand");
            else if (i == 2 && sb.length() != 0)
                sb.append(" Million");
            else if (i == 3 && sb.length() != 0)
                sb.append(" Billion");
            res.insert(0, sb);
        }
        return res.substring(1);
    }
}
