package other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 最大连续1的个数3
 *给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 *
 * 返回仅包含 1 的最长（连续）子数组的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 *
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1 
 */
public class A1004 {
    public static void main(String[] args) {
        int[] A = {1,1,1,0,0,0,1,1,1,1,0};
        System.out.println(longestOnes2(A, 2));
        int[] B = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        System.out.println(longestOnes2(B, 3));
        int[] C = {0,0,1,1,1,0,0};
        System.out.println(longestOnes2(C, 0));
    }
    // 队列
    public static int longestOnes(int[] A, int K) {
        int l = 0, r = 0, len = A.length;
        int max = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        do {
            if (A[r] == 1) {
                max = Math.max(max, r - l + 1);
                r++;
            } else {
                if (deque.size() < K) {
                    deque.addLast(r);
                    max = Math.max(max, r - l + 1);
                    r++;
                } else {
                    if (deque.size() > 0) {
                        int pre = deque.pollFirst();
                        l = pre + 1;
                        deque.addLast(r);
                        max = Math.max(max, r - l + 1);
                        r++;
                    } else {
                        r++;
                        l = r;
                    }
                }
            }
        } while (r < len);
        return max;
    }

    public static int longestOnes2(int[] A, int K) {
        int l = 0, r = 0, len = A.length;
        int zero = 0;
        int max = 0;
        do {
            if (A[r] == 1) {
                max = Math.max(max, r - l + 1);
                r++;
            } else {
                if (zero < K) {
                    zero++;
                    max = Math.max(max, r - l + 1);
                    r++;
                } else {
                    while (zero >= K && l <= r) {
                        if (A[l] == 0 && zero > 0) {
                            zero--;
                        }
                        l++;
                    }
                    max = Math.max(max, r - l + 1);
                    if (l > r) {
                        r = l;
                    }
                }
            }
        } while (r < len);
        return max;
    }
}
