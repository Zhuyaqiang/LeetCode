package other;

/**
 * 秋叶收藏集
 * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
 * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
 * 示例 1：
 * 输入：leaves = "rrryyyrryyyrr"
 * 输出：2
 * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
 * 示例 2：
 * 输入：leaves = "ryr"
 * 输出：0
 * 解释：已符合要求，不需要额外操作
 * 提示：
 * 3 <= leaves.length <= 10^5
 * leaves 中只包含字符 'r' 和字符 'y'
 */

public class LCP19 {
    // https://leetcode-cn.com/problems/UlBDOe/solution/java-quan-zhu-shi-li-jie-dong-tai-gui-hua-by-leetc/
    public int minimumOperations(String leaves) {
        if (leaves == null || leaves.equals(""))
            return 0;
        int len = leaves.length();
        int[][] dp = new int[len][3]; // dp[i][j]表示从0至第i片树叶的j状态需要的次数, j=0: 左边, j=1: 中间, j=2: 右边
        int isYell = 0;
        char[] chars = leaves.toCharArray();
        // 第一个叶子必须是左边, 所以只需判断是否是黄叶子
        dp[0][0] = chars[0] == 'r' ? 0 : 1;
        // 第一个叶子不可能是中间和右边, 置无效, 第二个叶子不能是右边, 置无效
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            isYell = chars[i] == 'y' ? 1 : 0;
            dp[i][0] = dp[i-1][0] + isYell;
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]) + (1-isYell);
            // 右边部分必须i大于1
            if (i > 1) {
                dp[i][2] = Math.min(dp[i-1][1], dp[i-1][2]) + isYell;
            }
        }
        return dp[len-1][2];
    }
}
