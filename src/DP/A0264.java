package DP;

/**
 * 丑数2
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 * 1 是丑数。
 * n 不超过1690。
 */
public class A0264 {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }
    public static int nthUglyNumber(int n) {
        int[] ugly = new int[1690];
        ugly[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < 1690; i++) {
            int temp = Math.min(ugly[i2] * 2, Math.min(ugly[i3] * 3, ugly[i5] * 5));
            if (temp == ugly[i2] * 2) i2 ++;
            if (temp == ugly[i3] * 3) i3 ++;
            if (temp == ugly[i5] * 5) i5 ++;
            ugly[i] = temp;
        }
        return ugly[n-1];
    }
}
