package other;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 有多少小于当前数字的数字
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * 以数组形式返回答案。
 * 示例 1：
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 示例 2：
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 示例 3：
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 * 提示：
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 * 通过次数33,444提交次数40,580
 */
public class A1365 {
    // 暴力
    public int[] smallerNumbersThanCurrent(int[] nums) {
        if (nums == null)
            return null;
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = 0; j < len; j++) {
                if (i == j)
                    continue;
                if (nums[i] > nums[j])
                    count ++;
                if (j < i && nums[j] > nums[i] && res[j] == 0)
                    break;
            }
            res[i] = count;
        }
        return res;
    }
    // 排序后统计
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        if (nums == null)
            return null;
        int len = nums.length;
        int[][] count = new int[len][2];
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            count[i][0] = nums[i];
            count[i][1] = i;
        }
        int pre = -1;
        Arrays.sort(count, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < len; i++) {
            if (pre == -1 || count[i][0] != count[i-1][0]) {
                pre = i;
            }
            res[count[i][1]] = pre;
        }
        return res;
    }
    // 计数统计
    // 值域[0,100]
    public int[] smallerNumbersThanCurrent3(int[] nums) {
        if (nums == null)
            return null;
        int len = nums.length;
        int[] count = new int[101];
        for (int i = 0; i < len; i++) {
            count[nums[i]] ++;
        }
        for (int i = 1; i < 101; i++)
            count[i] = count[i-1] + count[i];
        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0)
                ret[i] = 0;
            else {
                ret[i] = count[nums[i]-1];
            }
        }
        return ret;
    }
}
