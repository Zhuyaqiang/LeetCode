package other;

import java.util.Arrays;

/**
 * 1300. 转变数组后最接近目标值的数组和
 *
 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 请注意，答案不一定是 arr 中的数字。
 示例 1：
 输入：arr = [4,9,3], target = 10
 输出：3
 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 示例 2：
 输入：arr = [2,3,5], target = 10
 输出：5
 示例 3：
 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 输出：11361
 */
public class A1300 {
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int len = arr.length, res = -1, sum = 0, rest = len;
        for (int i = 0; i < len; i++, rest--) {
            int r = (target - sum) / rest;
            if (r <= arr[i]) {
                res = r;
                break;
            }
            sum += arr[i];
        }

        if (res == -1)
            return arr[len-1];
        int dis = target - sum - res * rest;
        if (Math.abs(target - sum - (res + 1) * rest) < dis) return res+1;
        else return res;
    }
}
