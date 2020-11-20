package other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * 视频拼接
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 * 示例 1：
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 * 示例 2：
 * 输入：clips = [[0,1],[1,2]], T = 5
 * 输出：-1
 * 解释：
 * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 * 示例 3：
 * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * 输出：3
 * 解释：
 * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 * 示例 4：
 * 输入：clips = [[0,4],[2,8]], T = 5
 * 输出：2
 * 解释：
 * 注意，你可能录制超过比赛结束时间的视频。
 */
public class A1024 {
    public static void main(String[] args) {
//        int[][] clips = {{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
//        System.out.println(videoStitching(clips, 10));
//        int[][] clips2 = {{0,1},{1,2}};
//        System.out.println(videoStitching(clips2, 5));
        int[][] clips3 = {{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
        System.out.println(videoStitching(clips3, 9));
        int[][] clips4 = {{0,4},{2,8}};
        System.out.println(videoStitching(clips4, 5));
    }

    // 动态规划
    public static int videoStitching(int[][] clips, int T) {
        // dp[i]表示比赛的前i段需要几个视频片段拼接而成
        int[] dp = new int[T+1];
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                // 如果当前clip包含了T, 则判断是否将该clip算入结果
                if (clip[0] < i && clip[1] >= i)
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }

    // 贪心
    // 对于每一个位置i, 记录以其为左端点的子区间中最远的右端点
    public static int videoStitching2(int[][] clips, int T) {
        int[] max = new int[T];
        for (int[] clip : clips) {
            if (clip[0] < T)
                max[clip[0]] = Math.max(max[clip[0]], clip[1]);
        }

        int ret = 0, pre = 0, last = 0;
        // 每次我们枚举到一个新位置，我们都用max[i]来更新last。如果更新后last== i，那么说明下一个位置无法被覆盖，我们无法完成目标。
        for (int i = 0; i < T; i++) {
            last = Math.max(last, max[i]);
            if (i == last)
                return -1;
            if (i == pre) {
                ret ++;
                pre = last;
            }
        }
        return ret;
    }
}
