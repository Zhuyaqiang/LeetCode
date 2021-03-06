package math;

/**
 * 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class A0007 {
    public int reverse(int x) {
        boolean flag = true;
        if (x < 0) {
            flag = false;
            x = -x;
        }
        long res = 0;
        while (x > 0) {
            int val = x % 10;
            x = x / 10;
            res = res * 10 + val;
        }
        return (int) (flag ? (res > Integer.MAX_VALUE ? 0 : res) : (-res < Integer.MIN_VALUE ? 0 : -res));
    }

    public static void main(String[] args) {
        System.out.println(reverse2(-123));
    }
    public static int reverse2(int x) {
        boolean flag = x > 0;
        int res = 0;
        while (x != 0) {
            int mod = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) {
                return 0;
            }
            res = res * 10;
            if (flag && res > Integer.MAX_VALUE - mod) {
                return 0;
            }
            if (!flag && res < Integer.MIN_VALUE - mod) {
                return 0;
            }
            res += mod;
        }
        return res;
    }
}