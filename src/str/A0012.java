package str;

/**
 * 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * 示例 1:
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class A0012 {
    public static void main(String[] args) {
        System.out.println(intToRoman3(3));
        System.out.println(intToRoman3(9));
        System.out.println(intToRoman3(58));
        System.out.println(intToRoman3(1994));
        System.out.println(intToRoman3(2200));
        System.out.println(intToRoman3(20));
        System.out.println(intToRoman3(60));
        System.out.println(intToRoman3(101));
    }
    public static String intToRoman2(int num) {
        int count = 0, tempNum = num;
        while (tempNum != 0) {
            count ++;
            tempNum /= 10;
        }
        String[][] trans = {{"I", "V", "IV", "IX"}, {"X", "L", "XL", "XC"}, {"C", "D", "CD", "CM"}, {"M"}};
        StringBuilder res = new StringBuilder();
        while (num != 0) {
            tempNum = num;
            while (tempNum / 10 > 0)
                tempNum /= 10;
            if (tempNum == 0) {
                num %= 10;
                continue;
            }
            else if (tempNum == 1)
                res.append(trans[--count][0]);
            else if (tempNum == 4)
                res.append(trans[--count][2]);
            else if (tempNum == 5)
                res.append(trans[--count][1]);
            else if (tempNum == 9)
                res.append(trans[--count][3]);
            else {
                StringBuilder temp = new StringBuilder();
                int coutNum = tempNum;
                if (coutNum > 5) {
                    temp = new StringBuilder(trans[--count][1]);
                    coutNum = coutNum - 5;
                } else {
                    --count;
                }
                while (coutNum > 0) {
                    temp.append(trans[count][0]);
                    coutNum --;
                }
                res.append(temp);
            }
            if (num >= 1000) {
                num -= tempNum * 1000;
                if (num < 100)
                    count--;
                if (num < 10)
                    count --;
            } else if (num >= 100) {
                num -= tempNum * 100;
                if (num < 10)
                    count --;
            } else if (num >= 10) {
                num -= tempNum *10;
            } else
                num = 0;
        }
        return res.toString();
    }
    // 对于给定整数使用能替代的最大整数
    public static String intToRoman3(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length && num >= 0; i++) {
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
    // 硬编码
    public static String intToRoman4(int num) {
        String[] thousands = {"","M","MM","MMM"};
        String[] hundreds = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String[] tens = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] ones = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        return thousands[num/1000] + hundreds[num%1000/100] + tens[num%100/10] + ones[num%10];
    }
}
