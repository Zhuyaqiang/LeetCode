package other;

import java.util.*;

/**
 * 重新安排行程
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 * 说明:
 * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 * 示例 1:
 * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * 示例 2:
 * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 */
public class A0332 {
    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("JFK", "SFO"));
        list.add(Arrays.asList("JFK", "ATL"));
        list.add(Arrays.asList("SFO", "ATL"));
        list.add(Arrays.asList("ATL", "JFK"));
        list.add(Arrays.asList("ATL", "SFO"));
        List<String> itinerary = findItinerary2(list);
        System.out.println(itinerary);
    }

    // 递归, 回溯
    public static List<String> res = new ArrayList<>();

    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<String>> map = new HashMap<>();
        int n = tickets.size();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (map.containsKey(from)) {
                List<String> tos = map.get(from);
                tos.add(to);
                map.put(from, tos);
            } else {
                map.put(from, new ArrayList<>(Arrays.asList(to)));
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            Collections.sort(value);
        }
        backtrack(map, n + 1, new ArrayList<>(Arrays.asList("JFK")));
        return res;
    }

    public static boolean flag = false;

    public static void backtrack(Map<String, List<String>> map, int total, List<String> curr) {
        if (curr.size() == total) {
            flag = true;
            res = new ArrayList<>(curr);
            return;
        }
        if (curr.size() == 0)
            return;
        String currTo = curr.get(curr.size() - 1);
        List<String> newTos = map.get(currTo);
        if (newTos == null) {
            return;
        }
        int index = 0;
        while (!flag && index < newTos.size()) {
            String newTo = newTos.get(index);
            newTos.remove(index);
            curr.add(newTo);
            backtrack(map, total, new ArrayList<>(curr));
            curr.remove(curr.size() - 1);
            newTos.add(index, newTo);
            index++;
        }
    }


    // 深度优先搜索
    // 由题可知一定有能够走完所有行程的方案
    // 因此孤立点(即可进不可出的点)一定只有一个且为所有可行方案的终点
    // 因此先遍历节点的所有边再入栈, 一定能保证孤立点是第一个入栈的, 之后再逆序即是结果
    public static Map<String, PriorityQueue<String>> map = new HashMap<>();
    public static List<String> ans = new ArrayList<>();

    public static List<String> findItinerary2(List<List<String>> tickets) {
        String from, to;
        for (List<String> ticket : tickets) {
            from = ticket.get(0);
            to = ticket.get(1);
            if (map.containsKey(from)) {
                map.get(from).add(to);
            } else {
                PriorityQueue<String> queues = new PriorityQueue<>();
                queues.offer(to);
                map.put(from, queues);
            }
        }
        dfs("JFK");
        Collections.reverse(ans);
        return ans;
    }

    public static void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String newCurr = map.get(curr).poll();
            dfs(newCurr);
        }
        ans.add(curr);
    }
}
