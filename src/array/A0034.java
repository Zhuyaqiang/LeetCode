package array;

public class A0034 {
    public static void main(String[] args) {
        int[] nums = {1};
        int[] ints = searchRange(nums, 1);
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
