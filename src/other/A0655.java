package other;

/**
 * 非递减数列
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 *
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *  
 *
 * 说明：
 *
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 */
public class A0655 {
    public static void main(String[] args) {
        int[] nums = {4, 2, 3};
        System.out.println(checkPossibility2(nums));
        int[] nums1 = {4, 2, 1};
        System.out.println(checkPossibility2(nums1));
        int[] nums2 = {5, 7, 1, 8};
        System.out.println(checkPossibility2(nums2));
        int[] nums3 = {5, 7, 1, 8};
        System.out.println(checkPossibility2(nums3));
    }
    // 当出现递减时(nums[i] > nums[i+1]), 要么 nums[i] = nums[i+1], 要么nums[i+1] = nums[i]. 但是能不修改nums[i+1]就不修改nums[i+1]
    // 当nums[i+1] < nums[i-1[的时候不能修改nums[i],  否则会导致nums[i-1] < nums[i]
    public static boolean checkPossibility2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        int len = nums.length;
        boolean flag = false;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (flag) {
                    return false;
                }
                flag = true;
                if (i > 0 && nums[i + 1] < nums[i - 1]) {
                    nums[i + 1] = nums[i];
                } else {
                    nums[i] = nums[i + 1];
                }
            }
        }
        return true;
    }
    public static boolean checkPossibility(int[] nums) {
        int len = nums.length;
        boolean flag = false;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (flag) {
                    return false;
                }
                if (i > 0) {
                    if (nums[i + 1] < nums[i - 1]) {
                        nums[i + 1] = nums[i];
                    } else {
                        nums[i] = nums[i + 1];
                    }
                } else {
                    nums[i] = nums[i + 1];
                }
                flag = true;
            }
        }
        return true;
    }
}
