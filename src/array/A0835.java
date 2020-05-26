package array;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 图像重叠
 * 给出两个图像 A 和 B ，A 和 B 为大小相同的二维正方形矩阵。（并且为二进制矩阵，只包含0和1）。
 * 我们转换其中一个图像，向左，右，上，或下滑动任何数量的单位，并把它放在另一个图像的上面。之后，该转换的重叠是指两个图像都具有 1 的位置的数目。
 * （请注意，转换不包括向任何方向旋转。）
 * 最大可能的重叠是什么？
 * 示例 1:
 * 输入：A = [[1,1,0],
 *           [0,1,0],
 *           [0,1,0]]
 *      B = [[0,0,0],
 *           [0,1,1],
 *           [0,0,1]]
 * 输出：3
 * 解释: 将 A 向右移动一个单位，然后向下移动一个单位。
 * 注意: 
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 * 0 <= A[i][j], B[i][j] <= 1
 */
public class A0835 {
    // 暴力法, 超时
    // 先在两个ArrayList中存储A和B(时间复杂度O(N2))
    // 两层遍历计算aList中每一个点和bList中每一个点的偏移量(时间复杂度O(N4))
    // 在两层遍历中判断每一次的偏移量能产生多少个A和B的重叠(时间复杂度O(N6))
    public int largestOverlap(int[][] A, int[][] B) {
        int m = A.length;
        List<Point> aList = new ArrayList<>(), bList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1) aList.add(new Point(i, j));
                if (B[i][j] == 1) bList.add(new Point(i, j));
            }
        }
        int ans = 0;
        Set<Point> seen = new HashSet<>();
        for (Point aPoint : aList) {
            for (Point bPoint : bList) {
                Point delta = new Point(bPoint.x - aPoint.x, bPoint.y - aPoint.y);
                if (!seen.contains(delta)) {
                    seen.add(delta);
                    int cand = 0;
                    for (Point point : aList) {
                        if (bList.contains(new Point(point.x + delta.x, point.y + delta.y)))
                            cand ++;
                    }
                    ans = Math.max(ans, cand);
                }
            }
        }
        return ans;
    }

    // 循环遍历A, 对A中为1的每一个点, 循环遍历B, 记录对A中为1的每一个点至B中为1的每一个点的偏移量出现的次数
    // 最后偏移量出现的最大次数即答案
    // 时间复杂度O(N4)
    public int largestOverlap2(int[][] A, int[][] B) {
        int m = A.length;
        int[][] count = new int[2*m+1][2*m+1];  // 针对偏移量是负值的问题, 防止溢出
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1) {
                    for (int k = 0; k < m; k++) {
                        for (int l = 0; l < m ;l++) {
                            if (B[k][l] == 1) {
                                count[k-i+m][l-j+m] += 1;
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int[] ints : count) {
            for (int anInt : ints) {
                ans = Math.max(ans, anInt);
            }
        }
        return ans;
    }
}
