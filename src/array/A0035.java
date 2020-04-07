package array;

public class A0035 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        System.out.println(searchInsert2(nums, 0));
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
}
