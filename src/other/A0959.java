package other;

/**
 * 由斜杠划分区域
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 *
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 *
 * 返回区域的数目。
 *
 *提示：
 *
 * 1 <= grid.length == grid[0].length <= 30
 * grid[i][j] 是 '/'、'\'、或 ' '。
 *     0
 *  3     1
 *     2
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regions-cut-by-slashes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A0959 {
    public static void main(String[] args) {
        String[] strs = {" /", "/ "};
        System.out.println(regionsBySlashes(strs));
    }
    public static int regionsBySlashes(String[] grid) {
        int n = grid.length;
        Union union = new Union(4 * n * n);
        for (int i = 0; i < n; i++) {
            char[] chars = grid[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                int indexStart = (i * n + j) * 4;
                if (chars[j] == '\\') {
                    union.merge(indexStart, indexStart + 1);
                    union.merge(indexStart + 2, indexStart + 3);
                } else if (chars[j] == '/') {
                    union.merge(indexStart, indexStart + 3);
                    union.merge(indexStart + 1, indexStart + 2);
                } else {
                    union.merge(indexStart, indexStart + 1);
                    union.merge(indexStart + 1, indexStart + 2);
                    union.merge(indexStart + 2, indexStart + 3);
                }
                if (j < n - 1) {
                    union.merge(indexStart + 1, (i * n + j + 1) * 4 + 3);
                }
                if (i < n - 1) {
                    union.merge(indexStart + 2, ((i + 1) * n + j) * 4);
                }
            }
        }
        return union.getCount();
    }
    public static class Union {
        private int[] parent;
        private int count;
        public Union(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            count = n;
        }

        public int getCount() {
            return count;
        }

        public int find(int val) {
            if (parent[val] != val) {
                parent[val] = find(parent[val]);
            }
            return parent[val];
        }

        public void merge(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                parent[rootA] = rootB;
                count--;
            }
        }
    }
}
