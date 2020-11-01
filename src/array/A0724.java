package array;

/**
 * 寻找数组的中心索引
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * 示例 1:
 * 输入:
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出: 3
 * 解释:
 * 索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * 示例 2:
 * 输入:
 * nums = [1, 2, 3]
 * 输出: -1
 * 解释:
 * 数组中不存在满足此条件的中心索引。
 * 说明:
 * nums 的长度范围为 [0, 10000]。
 * 任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
 */
public class A0724 {
    public static void main(String[] args) {
        int[] nums = {1, 7, 3, 6, 5, 6};
        System.out.println(pivotIndex2(nums));
    }

    public static int rPivotIndex(int[] nums) {
        int len = nums.length;
        int sumTotal = 0;
        for (int num : nums) {
            sumTotal += num;
        }
        int sumLeft = 0;
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                sumLeft += nums[i-1];
            }
            if (sumTotal - sumLeft - nums[i] == sumLeft)
                return i;
        }
        return -1;
    }

    public static int pivotIndex(int[] nums) {
        int len = nums.length;
        int[] right = new int[len];
        if (len == 0)
            return -1;
        right[len-1] = 0;
        for (int i = len-2; i >= 0; i--)
            right[i] = right[i+1] + nums[i+1];
        int left = 0;
        int res = -1;
        for (int i = 1; i < len; i++) {
            left = left + nums[i-1];
            if (left == right[i]) {
                res = i;
                break;
            }
        }
        return res;
    }

    public static int pivotIndex2(int[] nums) {
        int len = nums.length;
        int[] right = new int[len];
        if (len == 0)
            return -1;
        int sumLeft = 0;
        for (int i = 0; i < len-1; i ++) {
            sumLeft += nums[i];
        }
        int sumRight = 0;
        int index = -1;
        for (int i = len-1; i > 0; i--) {
            sumRight += nums[i];
            sumLeft -= nums[i-1];
            System.out.println("right: " + sumRight + ", left: " + sumLeft + ", i: " + i);
            if(sumLeft == sumRight) {
                index = i-1;
                break;
            }
        }
        return index;
    }

    /**
     * sumLeft + sumRight + nums[p] = sumTotal;
     * sumLeft = sumRight
     * 可以得出 sumLeft * 2 + nums[p] = sumTotal;
     *
     * 作者：guo-shuai-yin
     * 链接：https://leetcode-cn.com/problems/find-pivot-index/solution/zhuan-hua-wei-qiu-jie-sunleft-2-numsp-sumtotal-qiu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static int pivotIndex3(int[] nums) {
        int sumTotal = 0;
        int sumLeft = 0;
        for (int i = 0; i < nums.length; i++) {
            sumTotal += nums[i];
        }
        for (int p = 0; p < nums.length; p++) {

            if (sumLeft * 2 == sumTotal - nums[p]) {
                return p;
            }
            sumLeft += nums[p];
        }
        return -1;
    }
}
