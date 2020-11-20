package array;

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
        int[] nums = {2,0,2,1,1,0};
        sortColors3(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    // 第一遍遍历统计个数
    // 第二遍遍历写入
    public static void sortColors(int[] nums) {
        int len = nums.length;
        int[] count = new int[3];
        Arrays.fill(count, 0);
        for (int i = 0; i < len; i++) {
            switch (nums[i]) {
                case 0:
                    count[0]++;
                    break;
                case 1:
                    count[1]++;
                    break;
                default:
                    count[2]++;
            }
        }
        int index = 0;
        for (int j = 0; j < count.length; j++) {
            for (int i = 0;i < count[j];i++) {
                nums[index] = j;
                index ++;
            }
        }
    }
    // 三指针法, 一次遍历
    public static void sortColors2(int[] nums) {
        int len = nums.length;
        int curr = 1, left = 0, right = len-1;
        while (curr <= right) {
            if (nums[curr] == 1)
                curr++;
            else if (nums[curr] == 0) {
                if (curr == left)
                    curr++;
                else {
                    int temp = nums[curr];
                    nums[curr] = nums[left];
                    nums[left] = temp;
                    left++;
                }
            } else {
                int temp = nums[curr];
                nums[curr] = nums[right];
                nums[right] = temp;
                right --;
            }
        }
    }
    // 快排
    public static void sortColors3(int[] nums) {
        quickSort(nums, 0, nums.length-1);

    }
    public static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int l = start, r = end, pivot = nums[start];
            while (l < r) {
                while (l < r && nums[r] >= pivot) r--;
                if (l < r) {
                    nums[l] = nums[r];
                    l++;
                }
                while (l < r && nums[l] < pivot) l++;
                if (l < r) {
                    nums[r] = nums[l];
                    r--;
                }
            }
            nums[l] = pivot;
            quickSort(nums, start, l - 1);
            quickSort(nums, l + 1, end);
        }
    }
}
