package array;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class A0283 {
    public static void main(String[] args) {
        int[] nums = {0,0,1};
        removeZeroes3(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    // 暴力法
    public static void moveZeroes(int[] nums) {
        int len = nums.length;
        int count = 0, index = 0;
        while (index + count < len) {
            if (nums[index] == 0) {
                for (int i = index; i < len-1-count; i++) {
                    nums[i] = nums[i+1];
                }
                nums[len-1-count] = 0;
                count ++;
                index --;
            }
            index ++;
        }
    }
    // 双指针, 移动非零元素
    public static void moveZeroes2(int[] nums) {
        int len = nums.length;
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }
        for (int i = j; j < len; j++)
            nums[j] = 0;
    }
    // 双指针, 交换
    public static void removeZeroes3(int[] nums) {
        int len = nums.length;
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }
}
