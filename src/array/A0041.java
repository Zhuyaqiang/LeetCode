package array;

public class A0041 {
    public static void main(String[] args) {
        int[] nums = {1,2,0};
        System.out.println(firstMissingPositive(nums));
    }
    public static int firstMissingPositive(int[] nums) {
        if (nums.length == 0 || nums == null)
            return 1;
        int len = nums.length;
        int contains = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1)
                contains ++;
        }
        if (contains == 0)
            return 1;
        if (len == 1)
            return 2;
        for (int i = 0; i < len; i++) {
            if (nums[i] < 1 || nums[i] > len)
                nums[i] = 1;
        }
        for (int i = 0; i < len; i++) {
            int temp = Math.abs(nums[i]);
            if (temp == len)
                nums[0] = -Math.abs(nums[0]);
            else
                nums[temp] = -Math.abs(nums[temp]);
        }
        for (int i = 1; i < len; i++)
            if (nums[i] > 0)
                return i;
        if (nums[0] > 0)
            return len;
        return len + 1;
    }
}
