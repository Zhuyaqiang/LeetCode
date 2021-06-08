package other;

import java.util.*;

/**
 * 除法求值
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个
 * Ai 或 Bi 是一个表示单个变量的字符串。
 * <p>
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * <p>
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * <p>
 *  
 * <p>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x",
 * "x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 示例 2：
 * <p>
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc",
 * "cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * 示例 3：
 * <p>
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 */
public class A0399 {
    public static void main(String[] args) {
        List<List<String>> equa = new ArrayList<>();
        equa.add(Arrays.asList("a", "b"));
        equa.add(Arrays.asList("b", "c"));
        List<List<String>> qua = new ArrayList<>();
        qua.add(Arrays.asList("a", "c"));
        System.out.println(Arrays.toString(rCalcEquation(equa, new double[] {2.0, 3.0}, qua)));
    }
    public static double[] rCalcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int[] parent = new int[26];
        double[] weight = new double[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
            weight[i] = 1.0d;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < equations.size(); i++) {
            int x = equations.get(i).get(0).charAt(0) - 'a';
            int y = equations.get(i).get(1).charAt(0) - 'a';
            set.add(x);
            set.add(y);
            union(parent, weight, x, y, values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            int x = queries.get(i).get(0).charAt(0) - 'a';
            int y = queries.get(i).get(1).charAt(0) - 'a';
            if (!set.contains(x) || !set.contains(y)) {
                res[i] = -1.0d;
                continue;
            }
            if (find(parent, x, weight) == find(parent, y, weight)) {
                res[i] = weight[x] / weight[y];
            } else {
                res[i] = -1.0d;
            }
        }
        return res;
    }
    public static void union(int[] parent, double[] weight, int x, int y, double val) {
        int rootX = find(parent, x, weight);
        int rootY = find(parent, y, weight);
        if (rootX != rootY) {
            parent[rootX] = parent[rootY];
            weight[x] = val * weight[y] / weight[x];
        }
    }
    public static int find(int[] parent, int val, double[] weight) {
        if (parent[val] != val) {
            int origin = parent[val];
            parent[val] = find(parent, parent[val], weight);
            weight[val] = weight[val] * weight[origin];
        }
        return parent[val];
    }









    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equLen = equations.size();
        UnionFind unionFind = new UnionFind(equLen * 2);
        // 字符和id的映射
        Map<String, Integer> map = new HashMap<>();
        int id = 0;

        // 建立并查集
        for (int i = 0; i < equLen; i++) {
            List<String> equation = equations.get(i);

            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!map.containsKey(var1)) {
                map.put(var1, id++);
            }

            if (!map.containsKey(var2)) {
                map.put(var2, id++);
            }

            unionFind.union(map.get(var1), map.get(var2), values[i]);
        }

        int quLen = queries.size();
        double[] res = new double[quLen];
        for (int i = 0; i < quLen; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = map.get(var1);
            Integer id2 = map.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    class UnionFind {
        private int[] parent;
        private double[] weight;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = parent[rootY];
            weight[x] = weight[y] * value / weight[x];
        }

        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return x;
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}
