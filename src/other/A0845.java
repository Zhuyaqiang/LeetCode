package other;

/**
 * 数组中的最长山脉
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * 给出一个整数 数组 A，返回最长 “山脉” 的长度。
 * 如果不含有 “山脉” 则返回 0。
 * 示例 1：
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 * 提示：
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 */
public class A0845 {
    public static void main(String[] args) {
        int[] nums = {0,1,0,2,2};
        System.out.println(longestMountain(nums));
        int[] nums2 = {2,2,2};
        System.out.println(longestMountain(nums2));
        int[] nums3 = {9,8,7,6,5,4,3,2,1,0};
        System.out.println(longestMountain(nums3));
        int[] nums4 = {2,1,4,7,3,2,5};
        System.out.println(longestMountain(nums4));
    }
    public static int longestMountain(int[] A) {
        if (A == null || A.length <= 2)
            return 0;
        int len = A.length;
        boolean flag = false;
        int res = 0, currMax = 1;
        for (int i = 1; i < len; i++) {
            if (A[i] > A[i - 1]) {
                if (!flag) {
                    flag = true;
                    currMax = 1;
                }
                currMax ++;
            } else if (A[i] == A[i-1]){
                flag = false;
                currMax = 1;
            } else {
                if (flag) {
                    currMax++;
                    res = Math.max(res, currMax);
                }
                if (i < len-1 && A[i] < A[i+1])
                    flag = false;
            }
        }
        return res <= 2 ? 0 : res;
    }
}
