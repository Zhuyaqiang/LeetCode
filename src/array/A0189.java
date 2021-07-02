package array;

/**
 * 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 */
public class A0189 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        rorate3(nums, 3);
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + " ");
        System.out.println();
    }
    // 暴力法
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        for (int i = 0; i < k; i++) {
            int temp = nums[len-1];
            for (int j = len-1; j > 0; j--)
                nums[j] = nums[j-1];
            nums[0] = temp;
        }
    }
    // 使用额外数组
    public static void rorate2(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int[] temp = new int[k];
        for (int i = 0; i < k; i++)
            temp[i] = nums[len-k+i];
        for (int i = len-1; i >= k; i--) {
            nums[i] = nums[i-k];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }
    // 翻转
    public static void rorate3(int[] nums, int k) {
        k = k %  nums.length;
        if (k == 0 || nums.length == 0)
            return;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public static void reverse(int[] nums, int start, int end) {
        int len = nums.length;
        for (int i = start; i <= start + (end - start) / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[end - i + start];
            nums[end - i + start] = temp;
        }
    }
}
