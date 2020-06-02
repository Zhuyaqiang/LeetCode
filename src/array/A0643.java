package array;

/**
 * 子数组最大平均数1
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * 示例 1:
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * 注意:
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 */
public class A0643 {
    public static void main(String[] args) {
        int[] nums = {5};
        System.out.println(findMaxAverage3(nums, 1));
    }
    // 暴力法, 超时
    public static double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        double avg = Integer.MIN_VALUE;
        for (int i = 0; i <= len-k; i++) {
            double sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            avg = Math.max(avg, sum / k);
        }
        return avg;
    }

    // 滑动窗口
    public static double findMaxAverage2(int[] nums, int k) {
        double sum = 0;
        int index;
        for (index = 0; index < k; index++) {
            sum += nums[index];
        }
        double avg = sum / k;
        int len = nums.length;
        for (int i = index; i < len; i++) {
            sum = sum + nums[i] - nums[i-k];
            avg = Math.max(avg, sum / k);
        }
        return avg;
    }

    // 通过累加和
    public static double findMaxAverage3(int[] nums, int k) {
        int len = nums.length;
        double[] sum = new double[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i-1] + nums[i];
        }
        double avg = sum[k-1] / k;
        for (int i = k; i < len; i++) {
            avg = Math.max(avg, (sum[i] - sum[i-k]) / k);
        }
        return avg;
    }
}
