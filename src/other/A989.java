package other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

/**
 * 数组形式的整数加法
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 *
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 示例 2：
 *
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 *
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 *
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A989 {
    public static void main(String[] args) {
        int[] A = {1,2,0,0};
        System.out.println(addToArrayForm(A, 34));
    }
    public static List<Integer> addToArrayForm(int[] A, int K) {
        int bLen = String.valueOf(K).length();
        int[] B = new int[bLen];
        for (int i = bLen - 1; i >= 0; i--) {
            B[i] = K % 10;
            K = K / 10;
        }
        int c = 0;
        int aLen = A.length;
        if (aLen > bLen) {
            int[] temp = A;
            A = B;
            B = temp;
            int tempLen = aLen;
            aLen = bLen;
            bLen = tempLen;
        }
        int diff = bLen - aLen;
        List<Integer> res = new ArrayList<>();
        for (int i = bLen - 1; i >= 0; i--) {
            int j = i - diff;
            int val;
            if (j >= 0) {
                System.out.println(i + " " + j);
                val = A[j] + B[i] + c;
                if (val >= 10) {
                    val = val % 10;
                    c = 1;
                } else {
                    c = 0;
                }
            } else {
                val = B[i] + c;
                if (val >= 10) {
                    val = val % 10;
                    c = 1;
                } else {
                    c = 0;
                }
            }
            res.add(val);
        }
        if (c > 0) {
            res.add(1);
        }
        Collections.reverse(res);
        return res;
    }
}
