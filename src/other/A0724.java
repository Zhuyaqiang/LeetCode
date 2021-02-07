package other;

/**
 * 寻找数组的中心索引
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 *
 * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 *
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * nums = [1, 7, 3, 6, 5, 6]
 *         0  1  8  11 17 22  28
 * 输出：3
 * 解释：
 * 索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * 示例 2：
 *
 * 输入：
 * nums = [1, 2, 3]
 * 输出：-1
 * 解释：
 * 数组中不存在满足此条件的中心索引。
 */
public class A0724 {
    public static void main(String[] args) {
        int[] nums = {-1, -1, 1, 1, 1};
        System.out.println(pivotIndex(nums));
    }
    public static int pivotIndex(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        int[] sum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        if (sum[len] - sum[1] == 0) {
            return 0;
        }
        for (int i = 1; i < len - 1; i ++) {
            int diff = sum[len] - sum[i + 1];
            if (diff == sum[i]) {
                return i;
            }
        }
        if (sum[len - 1] == 0) {
            return len - 1;
        }
        return -1;
    }
}
