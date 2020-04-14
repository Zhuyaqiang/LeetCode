package array;

/**
 * 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
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
        int[] nums = {1,3,5,6};
        System.out.println(rSearchInsert(nums, 0));
    }
    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (target > nums[len-1])
            return len;
        if (target < nums[0])
            return 0;
        int l = 0, r = len-1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target) {
                if (nums[mid-1] < target)
                    return mid;
                r = mid - 1;
            } else if (nums[mid] < target) {
                if (nums[mid+1] > target)
                    return mid+1;
                l = mid + 1;
            }
        }
        return 0;
    }
    public static int searchInsert2(int[] nums, int target) {
        int len = nums.length;
        if (target > nums[len-1])
            return len;
        if (target < nums[0])
            return 0;
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l ) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target)
                l = mid + 1;
        }
        return l;
    }

    public static int rSearchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        int l = 0, r = len-1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return l;
    }
}
