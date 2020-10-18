package math;

/**
 * x的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class A0069 {
    public static void main(String[] args) {
        System.out.println(mySqrt2(4));
        System.out.println(mySqrt2(8));
        System.out.println(mySqrt2(2147395600));
        System.out.println(mySqrt2(2147483647));
    }
    // 暴力
    public static int mySqrt(int x) {
        if (x <= 1)
            return x;
        long index;
        for (index = 1; index < x; index++) {
            if (index * index == x) {
                index ++;
                break;
            } else if (index * index > x)
                break;
            else if (4 * index * index < x)
                index = 2 * index;
        }
        return (int)(index - 1);
    }
    // 二分查找
    public static int mySqrt2(int x) {
        if (x <= 1)
            return x;
        int l = 0, r = x, ans = 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else
                r = mid - 1;
        }
        return ans;
    }
}
