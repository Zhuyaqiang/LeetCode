package other;

/**
 * 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *  
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 *  
 * 提示：
 * 0 <= n <= 5 * 106
 * 通过次数90,833提交次数251,146
 */
public class A0204 {
    public static void main(String[] args) {
        System.out.println(countPrimes2(499979));
    }
    // 暴力枚举, 超时
    public static int countPrimes(int n) {
        if (n <= 2)
            return 0;
        int res = 1;
        for (int i = 3; i < n; i++) {
            boolean flag = false;
            for (int j = 2; j < i && 2 * j <= i; j++) {
                if (i % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                res ++;
        }
        return res;
    }
    // 枚举2
    // 如果y是x的因数, 那么x/y也是x的因数, 因此只需要判断x/y或y即可, 这两个数肯定有一个小于根号x
    public static int countPrimes2(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            boolean flag = false;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                ans ++;
        }
        return ans;
    }
    // 筛选, 如果x是质数, 那么大于x倍数的2x, 3x一定不是质数
    public static int countPrimes3(int n) {
        boolean[] bool = new boolean[n];
        for (int i = 2; i < n; i++) {
            // 对于质数i, 从i*i判断, 因为i*i之前的2*i, 3*i已经被i之前的数字判断过了
            if (!bool[i] && (long) i * i < n) {
                for (long j = i * i; j < n; j = j + i) {
                    bool[(int)j] = true;
                }
            }
        }
        int ans = 0;
         for (int i = 2; i < n; i++) {
             if (!bool[i])
                 ans ++;
         }
         return ans;
    }
}
