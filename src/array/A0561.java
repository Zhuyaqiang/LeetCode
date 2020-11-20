package array;

import java.util.Arrays;

/**
 * 数组拆分1
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
 * 示例 1:
 * 输入: [1,4,3,2]
 * 输出: 4
 * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
 * 提示:
 * n 是正整数,范围在 [1, 10000].
 * 数组中的元素范围在 [-10000, 10000].
 */
public class A0561 {
    /**
     * 策略就是先将数组从小到大排序
     * 然后最大的和第二大的一组(最大的值一定是取不到的，但是这样保证第二大的值可以被取到)
     * 第三大的值和第四大的值一组(第一大和第二大值被取走之后，剩余的数组中，第三大的值一定是取不到的，安排和第四大的值组成一对，可以保证第四大的值被选取)
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i+=2) {
            sum += nums[i];
        }
        return sum;
    }
}
