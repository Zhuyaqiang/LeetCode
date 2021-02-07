package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 交换字符串中的元素
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 * 示例 1:
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 示例 2：
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 示例 3：
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 * 提示：
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 */
public class A1202 {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(0, 3));
        list.add(Arrays.asList(1, 2));
        System.out.println(smallestStringWithSwaps("dcab", list));
    }
    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int len = s.length();
        int[] parent = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
        }
        for (List<Integer> pair : pairs) {
            connect(parent, pair.get(0), pair.get(1));
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int root = find(parent, i);
            if (map.containsKey(root)) {
                map.get(root).add(i);
            } else {
                map.put(root, new HashSet<>(Collections.singleton(i)));
            }
        }
        char[] res = new char[len];
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            Set<Integer> set = entry.getValue();
            int size = set.size();
            char[] temp = new char[size];
            int index = 0;
            for (Integer integer : set) {
                temp[index++] = s.charAt(integer);
            }
            index = 0;
            Arrays.sort(temp);
            for (Integer integer : set) {
                res[integer] = temp[index++];
            }
        }
        return new String(res);
    }

    public static int find(int[] parent, int x) {
        if (x != parent[x]) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public static void connect(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            parent[rootX] = parent[rootY];
        }
    }
}
