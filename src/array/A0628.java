package array;

/**
 * 三个数的最大乘积
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 */
public class A0628 {
    public static void main(String[] args) {
        int[] nums = {-4,-3,-2,-1,60};
        System.out.println(maximumProduct(nums));
    }
    // 先排序, 再对比三种情况
    public static int maximumProduct(int[] nums) {
        int len = nums.length;
        quickSort(nums, 0, len-1);
        return Math.max(nums[0] * nums[1] * nums[2], Math.max(nums[0] * nums[1] * nums[len-1], nums[len-1] * nums[len-2]* nums[len-3]));
    }

    public static void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int start = l, end = r, pivot = nums[start];
            while (start < end) {
                while (start < end && nums[end] >= pivot) end--;
                if (start < end) {
                    nums[start] = nums[end];
                    start++;
                }
                while (start < end && nums[start] <= pivot) start++;
                if (start < end) {
                    nums[end] = nums[start];
                    end--;
                }
            }
            nums[start] = pivot;
            quickSort(nums, l, start - 1);
            quickSort(nums, start + 1, r);
        }
    }

    // 找出最大的三个数和最小的两个数
    public static int maximumProduct2(int[] nums) {
        int oneMax, twoMax, threeMax;
        oneMax = threeMax = twoMax = Integer.MIN_VALUE;
        int oneMin, twoMin;
        oneMin = twoMin = Integer.MAX_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int val = nums[i];
            if (val <= oneMin) {
                twoMin = oneMin;
                oneMin = val;
            } else if (val <= twoMin) {
                twoMin = val;
            }
            if (val >= oneMax) {
                threeMax = twoMax;
                twoMax = oneMax;
                oneMax = val;
            } else if (val >= twoMax) {
                threeMax = twoMax;
                twoMax = val;
            } else if (val >= threeMax) {
                threeMax = val;
            }
        }
        return Math.max(oneMax * twoMax * threeMax, oneMax * oneMin * twoMin);
    }
}
