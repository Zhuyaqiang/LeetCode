package math;

import java.util.ArrayList;
import java.util.List;

/**
 * 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * 提示：
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 */
public class A0029 {
    public static void main(String[] args) {
        System.out.println(divide3(10, 3));
        System.out.println(divide3(7, -3));
        System.out.println(divide3(Integer.MAX_VALUE, 2));
//        System.out.println(divide3(-1010369383, -2147483648));
//        System.out.println(divide3(Integer.MIN_VALUE, -1));
    }
    // 加法, 超时
    public static int divide(int dividend, int divisor) {
        if (divisor == 1)
            return dividend;
        if (divisor == -1 && dividend != Integer.MIN_VALUE)
            return -dividend;
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        if (dividend == divisor)
            return 1;
        boolean flag;
        if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) {
            flag = false;
        }
        else
            flag = true;
        if (dividend < 0)
            dividend = -dividend;
        if (divisor < 0)
            divisor = - divisor;
        int count = 0, total = 0;
        while (total + divisor <= dividend) {
            count ++;
            total += divisor;
        }
        return flag ? count : -count;
    }
    // 除法计算思想
    public static int divide2(int dividend, int divisor) {
        if (divisor == 1)
            return dividend;
        if (divisor == -1 && dividend != Integer.MIN_VALUE)
            return -dividend;
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        boolean flag;
        long divid = dividend, divis = divisor;
        if (divid > 0 && divis < 0 || divid < 0 && divis > 0) {
            flag = false;
        }
        else
            flag = true;
        if (divid < 0)
            divid = -divid;
        if (divis < 0)
            divis = - divis;
        List<Long> list = new ArrayList<>();
        while (divid != 0) {
            list.add(divid % 10);
            divid = divid / 10;
        }
        long res = 0;
        int index = list.size() - 1;
        long temp = 0;
        while (index >= 0) {
            int count1 = 0;
            while (temp < divis && index >= 0) {
                temp = temp * 10 + list.get(index);
                index --;
                count1 ++;
            }
            if (count1 == 0)
                break;
            if (res != 0 && count1 > 1) {
                for (int i = 0; i < count1 - 1; i++)
                    res *= 10;
            }
            int count = 0;
            while (temp - divis >= 0) {
                count ++;
                temp -= divis;
            }
            res = res * 10 + count;
        }
        res = flag ? res : -res;
        return (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) ? 0 : (int)res;
    }

    public static int divide3(int dividend, int divisor) {
        if (divisor == 1)
            return dividend;
        if (divisor == -1)
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : - dividend;
        int sign = 1;
        if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0)
            sign = -1;
        // 都转化为负数
        long divid = dividend > 0 ? -dividend : dividend;
        long divis = divisor > 0 ? -divisor : divisor;
        long res = div(divid, divis);
        res = sign * res;
        return (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) ? 0 : (int)res;
    }

    private static long div(long dividend, long divisor) {
        if (dividend > divisor)
            return 0;
        long count = 1, tb = divisor;
        while (tb + tb >= dividend) {
            count = count + count;
            tb = tb + tb;
        }
        return count + div(dividend - tb, divisor);
    }
}
