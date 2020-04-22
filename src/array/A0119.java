package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 杨辉三角2
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 示例:
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class A0119 {
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> curr = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            curr.clear();
            curr.add(1);
            for (int j = 1; j < i; j++) {
                curr.add(pre.get(j-1) + pre.get(j));
            }
            if (i != 0)
                curr.add(1);
            pre = new ArrayList<>(curr);
        }
        return curr;
    }

    // 动态规划, 从后往前
    public static List<Integer> getRow2(int rowIndex) {
        int[] res = new int[rowIndex + 1];
        for (int i = 0; i <= rowIndex; i++) {
            res[i] = 1;
            for (int j = i; j > 1; j--) {
                res[j - 1] = res[j - 2] + res[j - 1];
            }
        }
        return Arrays.stream(res).boxed().collect(Collectors.toList());
    }
}
