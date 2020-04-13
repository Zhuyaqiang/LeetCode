package array;

/**
 *
 * 在一个排序数组中查找元素的第一个和最后一个值
 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 你的算法时间复杂度必须是 O(log n) 级别。
 如果数组中不存在目标值，返回 [-1, -1]。
 示例 1:
 输入: nums = [5,7,7,8,8,10], target = 8
 输出: [3,4]
 示例 2:
 输入: nums = [5,7,7,8,8,10], target = 6
 输出: [-1,-1]
 */
public class A0034 {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int[] ints = searchRange(nums, 6);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
    public static int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int start = 0, end = len - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                int l = mid, r = mid;
                while (l >= 0 && nums[l] == target)
                    l--;
                while (r < len && nums[r] == target)
                    r++;
                return new int[] {l+1, r-1};
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }
    public static int[] searchRange2(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == target) {
                for (int j = len-1; j >= i; j--) {
                    if (nums[j] == target)
                        return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }
}
