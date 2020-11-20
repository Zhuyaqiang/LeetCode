package other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 等式方程的可满足性
 *
 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
 示例 1：
 输入：["a==b","b!=a"]
 输出：false
 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 示例 2：
 输出：["b==a","a==b"]
 输入：true
 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
 示例 3：
 输入：["a==b","b==c","a==c"]
 输出：true
 示例 4：
 输入：["a==b","b!=c","c==a"]
 输出：false
 示例 5：
 输入：["c==c","b==d","x!=z"]
 输出：true
 提示：
 1 <= equations.length <= 500
 equations[i].length == 4
 equations[i][0] 和 equations[i][3] 是小写字母
 equations[i][1] 要么是 '='，要么是 '!'
 equations[i][2] 是 '='
 */
public class A0990 {
    public static void main(String[] args) {
//        String[] eq = {"a==b","b!=a"};
//        String[] eq = {"b==a","a==b"};
//        String[] eq = {"a==b","b==c","a==c"};
//        String[] eq = {"a==b","b!=c","c==a"};
        String[] eq = {"c==c","b==d","x!=z"};
        System.out.println(equationsPossible(eq));
    }

    public static boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char one = equation.charAt(0);
                char two = equation.charAt(3);
                merge(parent, one - 'a', two - 'a');
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char one = equation.charAt(0);
                char two = equation.charAt(3);
                if (findRoot(parent, one - 'a') == findRoot(parent, two - 'a'))
                    return false;
            }
        }
        return true;
    }

    // parent[i]储存i的根节点
    // 找到index的根节点
    public static int findRoot(int[] parent, int index) {
        if (index == parent[index])
            return index;
        else {
            // 压缩路径, 每次查找时都将被查节点指向最根节点
            parent[index] = findRoot(parent, parent[index]);
            return parent[index];
        }
    }

    // 放到并查集
    public static void merge(int[] parent, int a, int b) {
        parent[findRoot(parent, a)] = findRoot(parent, b);
    }
}
