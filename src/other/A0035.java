package other;

/**
 * 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class A0035 {
    public static void main(String[] args) {
//        int[] nums = {1};
//        System.out.println(searchInsert(nums, 1));
        int[] nums = {1,3,5,6};
        System.out.println(searchInsert(nums, 0));
    }
    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int l = 0, r = len - 1;
        if (target < nums[l])
            return 0;
        if (target > nums[r])
            return len;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return r+1;
    }
}
