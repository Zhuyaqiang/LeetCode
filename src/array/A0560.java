package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class A0560 {
    public static void main(String[] args) {
        int[] nums = {0,0,0,0,0,0,0,0,0,0};
        System.out.println(subarraySum2(nums, 0));
    }
    // 暴力法
    public static int subarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            int target = 0;
            for (int j = i; j < len; j++) {
                target += nums[j];
                if (target == k) {
                    count ++;
                }
            }
        }
        return count;
    }
    // 前缀和 + 哈希表
    // sum表示前i项和, 将其存入map中, 即map中储存了前i项和数值出现的次数
    // 对于区间[j,i]
    // 前i项和 - 前j项和 == k即表示[j+1, i]这个区间和为k, 即前j项和 = k - 前i项和, 若该值出现在map中, 则说明有符合条件的
    public static int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i ++) {
            sum += nums[i];
            if (sum == k)
                count++;
            if (map.containsKey(sum - k)) {
                count = count + map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
