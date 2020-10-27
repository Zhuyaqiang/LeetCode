package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * 示例:
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 * 和O51类似
 */
public class A0315 {
    public static void main(String[] args) {
        int[] nums = {5,2,6,1};
        List<Integer> integers = countSmaller2(nums);
        System.out.println(integers);
    }

    // 归并排序
    public static List<Integer> countSmaller2(int[] nums) {
        int len = nums.length;
        List<Integer> ret = new ArrayList<>();
        if (len == 0) {
            return ret;
        }
        if (len == 1) {
            ret.add(0);
            return ret;
        }
        int data[][] = new int[len][2];
        for (int i = 0; i < len; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        int[][] temp = new int[len][2];
        int[] res = new int[len];
        mergeSort(data, temp, 0, len - 1, res);
        for (int re : res) {
            ret.add(re);
        }
        return ret;
    }

    public static void mergeSort(int[][] data, int[][] temp, int l, int r, int[] res) {
        if (l == r)
            return;
        int mid = l + (r - l) / 2;
        mergeSort(data, temp, l, mid, res);
        mergeSort(data, temp, mid + 1, r, res);

        if (data[mid][0] <= data[mid + 1][0])
            return;
        mergeAndCount(data, temp, res, l, mid, r);
    }

    private static void mergeAndCount(int[][] data, int[][] temp, int[] res, int l, int mid, int r) {
        for (int q = l; q <= r; q++)
            temp[q] = data[q];
        int i = l, j = mid + 1;
        System.out.println(l + " " + r);
        for (int k = l; k <= r; k++) {
            System.out.println(k + " " + i + " " + j);
            if (i == mid + 1) {
                data[k] = temp[j++];
            } else if (j == r + 1) {
                data[k] = temp[i++];
                res[data[k][1]] += r - mid;
            } else if (temp[i][0] <= temp[j][0]) {
                data[k] = temp[i++];
                res[data[k][1]] += j - (mid + 1);
            } else {
                data[k] = temp[j++];
            }
        }
    }


    // 暴力法, 超时
    public static List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return new ArrayList<>();
        int[] res = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            int count = 0;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[i])
                    count ++;
                if (nums[j] == nums[i]) {
                    count += res[j];
                    break;
                }
                if (nums[j] > nums[i] && res[j] == 0)
                    break;
            }
            res[i] = count;
        }
        List<Integer> ans = new ArrayList<>();
        for (int re : res) {
            ans.add(re);
        }
        return ans;
    }
}
