package array;

public class A0026 {
    public static void main(String[] args) {
        int[] nums = {1,1,2};
        System.out.println("count: " + removeDuplicates(nums));
    }
    public static int removeDuplicates(int[] nums) {
        int len = nums.length, dup = 0;
        for (int i = 1; i < len-dup; i++) {
            if (nums[i] == nums[i-1]) {
                dup++;
                for (int j = i; j < len-1; j++) {
                    nums[j] = nums[j+1];
                }
                i--;
            }
        }
        return len-dup;
    }
    public static int removeDuplicates1(int[] nums) {
        if (nums.length < 1)
            return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
