import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 *     你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 *
 * 提示：
 *
 *     0 <= nums.length <= 105
 *     -109 <= nums[i] <= 109
 *     nums 是一个非递减数组
 *     -109 <= target <= 109
 */
public class A0034 {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums, 6)));
    }
    public static int[] searchRange(int[] nums, int target) {
        // 查找大于等于target的第一个位置
        // 查找大于target的第一个位置
        int len = nums.length;
        int l = 0, r = len - 1;
        int left = -1, right = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                left = mid;
                r = mid - 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        l = 0;
        r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (left == -1) {
            return new int[] {-1, -1};
        }
        if (right == -1) {
            return new int[] {left, len - 1};
        }
        return new int[] {left, right - 1};
    }
}
