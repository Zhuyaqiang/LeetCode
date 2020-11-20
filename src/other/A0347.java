package other;

import java.util.*;

/**
 * 前K个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 提示：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 */
public class A0347 {
    public static void main(String[] args) {
        int[] nums = {4,1,-1,2,-1,2,3};
        int[] ints = rTopKFrequent(nums, 2);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
    public static int[] rTopKFrequent(int[] nums, int k) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        int[][] res = new int[map.size()][2];
        int index = 0;
        for (Map.Entry entry : map.entrySet()) {
            res[index][0] = (int)entry.getKey();
            res[index][1] = (int)entry.getValue();
            index ++;
        }
        Arrays.sort(res, (o1, o2) -> {
            return o2[1] - o1[1];
        });
        int[] ans = new int[k];
        for (int i = 0; i < k; i++)
            ans[i] = res[i][0];
        return ans;
    }
    static class Model {
        int key;
        int val;
        Model(int key, int val) {
            this.key = key;
            this.val = val;
        }

    }
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Model[] models = new Model[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            models[index] = new Model(entry.getKey(), entry.getValue());
            index ++;
        }
        quickSort(models, 0, map.size() - 1);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = models[models.length - 1 - i].key;
        }
        return res;
    }

    public static void quickSort(Model[] models, int l, int r) {
        if (l < r) {
            int i = l, j = r;
            Model pivot = models[l];
            while (i < j) {
                while (i < j && models[j].val > pivot.val) j--;
                if (i < j) {
                    models[i] = models[j];
                    i++;
                }
                while (i < j && models[i].val < pivot.val) i++;
                if (i < j) {
                    models[j] = models[i];
                    j--;
                }
            }
            models[i] = pivot;
            quickSort(models, l, i-1);
            quickSort(models, i+1, r);
        }
    }
}
