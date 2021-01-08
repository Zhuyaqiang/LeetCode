package other;

import java.util.HashSet;
import java.util.Set;

/**
 * 省份数量
 */
public class A547 {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        if (n == 0) {
            return 0;
        }
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    merge(parent, i, j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            int val = find(i, parent);
            if (!set.contains(val)) {
                set.add(val);
                res++;
            }
        }
        return res;
    }

    public int find(int x, int[] parent) {
        if (x == parent[x]) {
            return x;
        } else {
            parent[x] = find(parent[x], parent);
            return parent[x];
        }
    }

    public void merge(int[] parent, int x, int y) {
        parent[find(x, parent)] = find(y, parent);
    }
}
