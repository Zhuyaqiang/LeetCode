package other;

import java.util.Arrays;

/**
 * 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 */
public class A0189 {
    // 法1
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int temp;
        for (int i = 0; i < k; i++) {
            temp = nums[len - 1];
            for (int j = len - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
//        int[] nums = {-1, -100, 3, 99};
        rotate2(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
    // 法2
    public static void rotate2(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        if (k == 0) {
            return;
        }
        int count = 0;
        int index = 0;
        int pre = nums[0];
        while (count < len) {
            int tempIndex = index;
            do {
                int nextIndex = (index + k) % len;
                int temp = nums[nextIndex];
                nums[nextIndex] = pre;
                pre = temp;
                index = nextIndex;
                count++;
            } while (tempIndex != index && count < len);
            index = (index + 1) % len;
            pre = nums[index];
        }
    }
}
