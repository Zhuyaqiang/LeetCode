package sort;

import java.util.Arrays;

/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
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
//        sortColors(nums);
//        System.out.println(Arrays.toString(nums));
//        int[] nums1 = {2,0,1};
//        sortColors(nums1);
//        System.out.println(Arrays.toString(nums1));
        int[] nums2 = {2,0,2,1,1,0};
        sortColors(nums2);
        System.out.println(Arrays.toString(nums2));
    }
    public static void sortColors(int[] nums) {
        int len = nums.length;
        int l = 0, r = len - 1, index = 0;
        while (index <= r) {
            // 遇0则交换 当前元素 和 p0空间的值，并 使得 p0指针 指向 下一个0应该存放的位置，遍历下一个元素, 由于p0指针之前指向的元素一定被index遍历过, 所以不用再次遍历
            if (nums[index] == 0) {
                nums[index] = nums[l];
                nums[l] = 0;
                l ++;
            }
            // 遇2则交换 当前元素 和 p2空间的值，并 使得 p2指针 指向 下一个2应该存放的位置，继续遍历 交换后的当前元素
            else if (nums[index] == 2) {
                nums[index] = nums[r];
                nums[r] = 2;
                r --;
                index --;
            }
            index ++;
        }
    }
}
