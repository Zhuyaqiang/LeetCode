package array;

/**
 * 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class A0033 {
    public static void main(String[] args) {
        int[] nums = {6,7,8,1,2,3,4,5};
        System.out.println(search(nums, 6));
    }
    public static int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0)
            return -1;
        if (len == 1)
            return nums[0] == target ? 0 : -1;
        int l = 0, r = len-1;
        int start = 0;
        if (nums[l] >= nums[r]) {
            while (l <= r) {
                int mid = (l + r) / 2;
                System.out.println("l: " + l + ", mid: " + mid + ", r: " + r);
                if (nums[mid] > nums[mid + 1]) {
                    start = mid + 1;
                    break;
                } else {
                    if (nums[mid] > nums[r])
                        l = mid + 1;
                    else
                        r = mid - 1;
                }
            }
        }
        System.out.println(start);
        if (start == 0)
            return binarySearch(nums, 0, len - 1, target);
        if (target < nums[0])
            return binarySearch(nums, start, len-1, target);
        else
            return binarySearch(nums, 0, start, target);
    }
    public static int binarySearch(int[] nums, int start, int end, int target) {
        int mid = (start + end) / 2;
        while (start <= end) {
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = (start + end) / 2;
        }
        return -1;
    }

    // nums[start] > nums[mid] (后半部分有序)
    //         nums[mid] < targe <= nums[end], 去后半部分查
    //         否则不在后半部分, 去前半部分查
    // nums[start] <= nums[mid] (前半部分有序)
    //          nums[start] <= target < num[end], 前半部分查
    //          否则不在前半部分, 去后半部分查
    public static int search2(int[] nums, int target) {
        int len = nums.length;
        if (len == 0)
            return -1;
        if (len == 1)
            return nums[0] == target ? 0 : -1;
        int start = 0, end = len-1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            } else {
                if (target >= nums[mid] && target < nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
