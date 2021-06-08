package other;

/**
 * 两整数之和
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 *
 * 示例 1:
 *
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 *
 * 输入: a = -2, b = 3
 * 输出: 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A0371 {
    // 无进位加法为异或
    // 进位为与左移一位
    public int getSum(int a, int b) {
        // a保留进位的值
        while (a != 0) {
            int res1 = a ^ b;
            int res2 = (a & b) << 1;
            a = res2;
            b = res1;
        }
        return b;
    }
}
