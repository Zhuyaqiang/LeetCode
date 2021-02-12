package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 杨辉三角2
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class A0119 {
    public static void main(String[] args) {
        System.out.println(getRow(3));
    }
    public static List<Integer> getRow(int rowIndex) {
        int[] ans = new int[rowIndex + 1];
        List<Integer> res = new ArrayList<>();
        Arrays.fill(ans, 1);
        for (int i = 2; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--) {
                ans[j] = ans[j] + ans[j - 1];
            }
        }
        for (int i = 0; i < rowIndex + 1; i++) {
            res.add(ans[i]);
        }
        return res;
    }
}
