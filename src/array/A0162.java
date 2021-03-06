package array;

/**
 * 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * 你的解法应该是 O(logN) 时间复杂度的。
 */
public class A0162 {
    // 暴力法, 复杂度O(n)
    public static int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1 || nums[0] > nums[1])
            return 0;
        if (nums[len-1] > nums[len-2])
            return len-1;
        for (int i = 1; i < len - 1; i ++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1])
                return i;
        }
        return 0;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(findPeakElement2(nums));
    }
    // 递归
    public static int res = 0;
    public static int findPeakElement2(int[] nums) {
        int len = nums.length;
        if (len == 1 || nums[0] > nums[1])
            return 0;
        if (nums[len-1] > nums[len-2])
            return len-1;
        divide(nums, 0, len-1);
        return res;
    }
    public static void divide(int[] nums, int start, int end) {
        int mid = start + (end - start) / 2;
        if (mid == start)
            return;
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
            res = mid;
            return;
        }
        divide(nums, start, mid);
        divide(nums, mid, end);
    }
    // 二分迭代, 因为尽头两边都是负无穷, 因此往比较mid和mid+1, 往较大的方向走一定能找到峰值
    public static int findPeakElement3 (int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid+1]) // 向左走, 且nums[mid]可能是峰值, 因此不能跳过mid
                r = mid;
            else // nums[mid] < nums[mid + 1] 向右走, 且nums[mid]不可能是峰值
                l = mid + 1;
        }
        return l;
    }

}
