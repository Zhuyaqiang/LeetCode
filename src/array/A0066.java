package array;

/**
 * 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class A0066 {
    public static void main(String[] args) {
    }
    public static int[] plusOne(int[] digits) {
        int c = 1;
        for (int i = digits.length-1; i >= 0; i--) {
            if (digits[i] + c == 10) {
                digits[i] = 0;
                c = 1;
            } else {
                digits[i] = digits[i] + c;
                c = 0;
            }
        }
        if (c == 0)
            return digits;
        else {
            int[] res = new int[digits.length+1];
            res[0] = c;
            for (int i = 1; i < res.length; i++)
                res[i] = digits[i-1];
            return res;
        }
    }
    public static int[] plusOne2(int[] digits) {
        int len = digits.length;
        for (int i = len-1; i >= 0; i--) {
            digits[i] ++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0)
                return digits;
        }
        digits = new int[len+1];
        digits[0] = 1;
        return digits;
    }
}
