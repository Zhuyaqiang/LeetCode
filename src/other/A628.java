package other;

import java.util.Arrays;

/**
 * 三个数的最大乘积
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 * <p>
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A628 {
    public int maximumProduct(int[] nums) {
        int len = nums.length;
        if (len == 3) {
            return nums[0] * nums[1] * nums[2];
        }
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[2],
                Math.max(nums[len - 1] * nums[len - 2] * nums[len - 3],
                Math.max(nums[0] * nums[1] * nums[len - 1], nums[len - 1] * nums[len - 2] * nums[0])));
    }

    // 如果全是非负数, 则最大的三个数乘积最大
    // 如果全是非正数, 则最大的三个数乘积最大
    // 如果有正有负, 则最大的是三个数乘积最大或最小的两个负数和最大的一个正数乘积最大
    // 因此找出最大的三个数和最小的两个数即可
    public int maximumProduct2(int[] nums) {
        int len = nums.length;
        if (len == 3) {
            return nums[0] * nums[1] * nums[2];
        }
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for (int val : nums) {
            if (val < min1) {
                min2 = min1;
                min1 = val;
            } else if (val < min2) {
                min2 = val;
            }
            if (val > max1) {
                max3 = max2;
                max2 = max1;
                max1 = val;
            } else if (val > max2) {
                max3 = max2;
                max2 = val;
            } else if (val > max3) {
                max3 = val;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
