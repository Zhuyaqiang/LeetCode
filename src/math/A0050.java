package math;

/**
 * Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class A0050 {
    public static int count = 0;
    public static void main(String[] args) {
//        System.out.println(myPow(2, 10));
//        System.out.println(myPow(2.1, 3));
//        System.out.println(myPow(2, -2));
        System.out.println(myPow(2, -2147483648));
    }
    public static double myPow(double x, int n) {
        count = 0;
        if (n == 0)
            return 1;
        long tempN = n;
        tempN = tempN < 0 ? -tempN : tempN;
        double res = x;

        res = backtrack(res, tempN);
        System.out.println("count: " + count);
        return (n > 0) ? res : 1 / res;
    }
    public static double backtrack(double a, long b) {
        count ++;
        if (b == 1)
            return a;
        if (b == 0)
            return 1;
        double tempA = a;
        long count = 1;
        while (count + count <= b) {
            a = a * a;
            count = count + count;
        }
        return a * backtrack(tempA, b - count);
    }
}
