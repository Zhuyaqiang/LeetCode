package array;

/**
 *  删除排序数组中的重复项
 *  给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class A0026 {
    public static void main(String[] args) {
        int[] nums = {1,1,2};
        System.out.println("count: " + removeDuplicates(nums));
    }
    // 暴力法
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
    // 双指针, i慢指针, j快指针
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
