package array;

/**
 * 非递减数列
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，总满足 array[i] <= array[i + 1]。
 * 示例 1:
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 */
public class A0665 {
    public static void main(String[] args) {
//        int[] nums = {4,2,3};
//        int[] nums = {2,3,3,2,4};
        int[] nums = {3,4,2,3};
        System.out.println(rCheckPossibility(nums));
    }
    // 在i和i+1递减时，判断i-1和i+1大小，若i-1 > i+1, i = i+1, 反之i = i-1
    public static boolean rCheckPossibility(int[] nums) {
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len-1; i++) {
            if (nums[i] > nums[i+1]) {
                int temp = nums[i];
                if (i >= 1)
                    nums[i] = nums[i-1];
                else
                    nums[i] = nums[i+1];
                if (nums[i] > nums[i+1]) {
                    nums[i] = temp;
                    nums[i+1] = nums[i];
                }
                count ++;
                if (count == 2)
                    return false;
            }
        }
        return true;
    }
    public static boolean checkPossibility(int[] nums) {
        int len = nums.length;
        boolean flag = false;
        if (nums.length >= 2) {
            if (nums[0] > nums[1]) {
                flag = true;
                nums[0] = nums[1];
            }
        }
        for (int i = 1; i < len-1; i++) {
            if (nums[i] > nums[i+1]) {
                if (flag)
                    return false;
                flag = true;
                // 处理 3,4,2,3   i = 2时的情况
                if (nums[i-1] > nums[i+1]) {
                    nums[i+1] = nums[i];
                } else {
                    nums[i] = nums[i+1];
                }
            }
        }
        return true;
    }
}
