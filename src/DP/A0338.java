package DP;

/**
 * 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 */
public class A0338 {
    public static void main(String[] args) {
        int[] ints = countBits2(8);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
    // 暴力法
    public static int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int count = 0, temp = i;
            while (temp != 0) {
                if (temp % 2 == 1)
                    count ++;
                temp /= 2;
            }
            res[i] = count;
        }
        return res;
    }
    // 动态规划
    // res[0] = 0,
    // res[0+1] = res[0] + 1,
    // res[0+2] = res[0] + 1, res[1+2] = res[1] + 1,
    // res[0+4] = res[0] + 1, res[1+4] = res[1] + 1, res[2+4] = res[2] + 1, res[3+4] = res[3] + 1
    // res[0+8] = res[0] + 1, res[1+8] = res[1] + 1, res[2+8] = res[2] + 1, res[3+8] = res[3] + 1, res[4+8] = res[4] + 1.....
    //
    // res[i+b] = res[i] + 1 (b为外层循环, b = 2 ^ x, x = 0,1,2....) (i为内层循环, 取值范围[1,b) )
    public static int[] countBits2(int num) {
        int[] res = new int[num + 1];
        int i = 0, b = 1;
        while (b <= num) {
            while (i < b && i + b <= num) {
                res[i + b] = res[i] + 1;
                i++;
            }
            i = 0;
            b *= 2;
        }
        return res;
    }
}
