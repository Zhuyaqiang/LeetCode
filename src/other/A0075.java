package other;

import java.util.Arrays;

/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class A0075 {
    public static void main(String[] args) {
//        int[] nums = {2,0,2,1,1,0};
//        sortColors2(nums);
//        System.out.println(Arrays.toString(nums));
//        int[] nums1 = {1,0,2};
//        sortColors2(nums1);
//        System.out.println(Arrays.toString(nums1));
//        int[] nums2 = {1,2,0};
//        sortColors2(nums2);
//        System.out.println(Arrays.toString(nums2));
        int[] nums3 = {2,0,1};
        sortColors2(nums3);
        System.out.println(Arrays.toString(nums3));
    }
    public static void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int i = 0; i < nums.length; i++) {
            switch (nums[i]) {
                case 0:
                    count[0]++;
                    break;
                case 1:
                    count[1]++;
                    break;
                case 2:
                    count[2]++;
                    break;
            }
        }
        int index = 0;
        for (int i = 0; i < 3; i++) {
            int tempIndex = index;
            for (; index < tempIndex + count[i]; index++)
                nums[index] = i;
        }
    }
    public static void sortColors2(int[] nums) {
        int len = nums.length;
        int l = 0, r = len-1;
        for (int i = 0; i <= r; i++) {
            if (nums[i] == 0) {
                nums[i] = nums[l];
                nums[l++] = 0;
            } else if (nums[i] == 2) {
                nums[i--] = nums[r];
                nums[r--] = 2;
            }
        }
    }
}
