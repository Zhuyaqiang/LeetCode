package array;

/**
 * 搜索旋转排序数组2
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * 示例 1:
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 */
public class A0081 {
    public static void main(String[] args) {
        int[] nums = {2,5,6,0,0,1,2};
        System.out.println(search2(nums, 2));
    }
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return true;
            // 去除一个重复
            if (nums[l] == nums[mid]) {
                l ++;
                continue;
            }
            if (nums[l] <= nums[mid]) {
                if (target >= nums[l] && target <= nums[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            } else {
                if (target >= nums[mid] && target <= nums[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }
        return false;
    }
    public static boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        if (nums.length == 1)
            return binarySearch(nums, target, 0, 1);
        int len = nums.length;
        int l = 0, r = len - 1, start = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid < nums.length-1 && nums[mid] > nums[mid + 1]) {
                start = mid + 1;
                break;
            }
            if (nums[l] == nums[mid]) {
                l ++;
                continue;
            }
            if (nums[l] > nums[mid]) {
                r = mid - 1;
            } else
                l = mid + 1;
        }
        // 从头开始查
        if (start == 0)
            return binarySearch(nums, target, 0, len - 1);
        // target只有可能在后半部分
        else if(target < nums[0]) {
            return binarySearch(nums, target, start, len - 1);
        }
        // target只有可能在前半部分
        return binarySearch(nums, target, 0, start - 1);
    }
    public static boolean binarySearch(int[] nums, int target, int l, int r) {
        while (l < nums.length && l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return true;
            else if (nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return false;
    }
}
