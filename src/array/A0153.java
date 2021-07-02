package array;

/**
 * 寻找旋转排序数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 你可以假设数组中不存在重复元素。
 * 示例 1:
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 */
public class A0153 {
    public static void main(String[] args) {
        int nums[] = {2,1};
//        int nums[] = {1};
//        int nums[] = {3,4,5,1,2};
        System.out.println(rFineMin(nums));
    }
    public static int rFineMin(int[] nums) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l < r) {
            // mid永远不会等于r
            int mid = l + (r - l) / 2;
            // 如果判断左值和中值的大小的话可能会出现l = mid = r-1, 所以应判断右值和中值
            if (nums[r] > nums[mid]) {
                r = mid;
            } else // 只可能是r != mid && nums[r] < nums[mid]
                l = mid + 1;
        }
        return nums[l];
    }
    public static int findMin(int[] nums) {
        int len = nums.length;
        if (len == 1)
            return nums[0];
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1])
                return nums[mid + 1];
            if (mid - 1 >= 0 && nums[mid - 1] > nums[mid])
                return nums[mid];
            if (nums[mid] > nums[r])
                l = mid + 1;
            else
                r = mid - 1;
        }
        return nums[l];
    }
}
