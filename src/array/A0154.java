package array;

/**
 * 寻找旋转排序数组中的最小值
 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 请找出其中最小的元素。
 注意数组中可能存在重复的元素。
 示例 1：
 输入: [1,3,5]
 输出: 1
 示例 2：
 输入: [2,2,2,0,1]
 输出: 0
 */
public class A0154 {
    public static void main(String[] args) {
        int[] nums = {2, 1};
        System.out.println(findMin(nums));
    }
    // 查找最小值应从右边判断
    // ①判断mid和l:
    //      nums[mid] > nums[l]有两种情况    1, 2, 3, -2, -1最小值在左边   1, 2, 3, 4, 5最小值在右边
    //      nums[mid] < nums[l]  最小值在左边
    //      nums[mid] == nums[l] 1,1,1,2,3  或 1,2
    // ②判断mid和r:
    //      nums[mid] > nums[r] 最小值在右边
    //      nums[mid] < nums[r] 最小值在左边(包含mid)
    //      nums[mid] == nums[r] 只可能是重复
    public static int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == nums[r])
                r--;
            else if (nums[mid] > nums[r])
                l = mid + 1;
            else
                r = mid;
        }
        return nums[l];
    }
}
