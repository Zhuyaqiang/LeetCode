package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 第 k 个排列
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class A0060 {
    public static void main(String[] args) {
        System.out.println(getPermutation(4,9));
    }
    public static int[] seen;
    public static boolean flag = false;
    public static String res = "";
    public static int count = 0;
    public static String getPermutation(int n, int k) {
        seen = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(seen, 0);
            backtrack("", i, 0, n, k);
        }
        return res;
    }
    public static void backtrack(String curr, int index, int used, int n, int k) {
        if (count >= k)
            return;
        seen[index] = 1;
        curr = curr + (char)((index + 1) + '0');
        System.out.println("now: " + curr);
        used ++;
        for (int i = 0; i < n; i++) {
            if (seen[i] == 0) {
                backtrack(new String(curr), i, used, n, k);
            }
        }
        if (used == n) {
            count ++;
            if (count == k) {
                res = curr;
            }
        }
        seen[index] = 0;
    }

    public static String getPermutation2(int n, int k) {
        seen = new int[n];
        int cen = 1;
        int remain = 1;
        int kk = n;
        while (kk != 1) {
            remain = remain * (kk - cen);
            kk--;
        }
        // remain是当前层之后有几个结果
        for (int i = 0; i < n; i++) {
            if(k > remain) {
                k -= remain;
            } else {
                return backtrack2("", n, k, seen, cen + 1, i);
            }
        }
        return "";
    }

    private static String backtrack2(String curr, int n, int k, int[] seen, int cen, int index) {
        if (curr.length() == n)
            return curr;
        seen[index] = 1;
        curr += (char) (index + '0');
        return "";
    }
}
