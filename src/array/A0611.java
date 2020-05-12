package array;

import java.util.Arrays;

/**
 * 有效的三角形个数
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 * 示例 1:
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 注意:
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 */
public class A0611 {
    public static void main(String[] args) {
        int[] nums = {1,1,3,4};
        System.out.println(triangleNumber2(nums));
    }
    public static int triangleNumber(int[] nums) {
        int len = nums.length;
        if (len < 3)
            return 0;
        int count = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    int a = nums[i] + nums[j];
                    int b = nums[j] + nums[k];
                    int c = nums[i] + nums[k];
                    if (a > nums[k] && b > nums[i] && c > nums[j])
                        count ++;
                }
            }
        }
        return count;
    }

    // 二分查找
    public static int triangleNumber2(int[] nums) {
        int len = nums.length;
        if (len < 3)
            return 0;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            for (int j = len - 1; j > i + 1; j--) {
                int index = binarySearch(nums, i+1, j-1, nums[j] - nums[i]+1);
                count += j - index;
            }
        }
        return count;
    }

    public static int binarySearch(int[] nums, int start, int end, int x) {
        int l = start, r = end;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= x)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return l;
    }

    // 双指针
    public static int triangleNumber3(int[] nums) {
        int len = nums.length;
        if (len < 3)
            return 0;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                int k = j + 1;
                while (nums[i] + nums[j] > k) k++;
                count += k - j - 1;
            }
        }
        return count;
    }
}
