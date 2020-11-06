package other;

import java.util.*;

/**
 * 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class A0127 {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("cog");
//        list.add("log");
//        list.add("lot");
//        list.add("dog");
//        list.add("dot");
//        list.add("hot");
//        System.out.println(ladderLength("hit", "cog", list));
        
        String[] strs = {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
        List<String> list = Arrays.asList(strs);
        System.out.println(ladderLength2("qa", "sq", list));

    }
    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord))
            return 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Queue<String> visited = new LinkedList<>();
        visited.add(beginWord);

        int res = 1;
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                String currWord = queue.poll();
                // 检查currWord修改一个字符后能否和endWord相等
                if (check2(currWord, endWord, queue, visited, wordSet)) {
                    return res + 1;
                }
            }
            res ++;
        }
        return 0;
    }

    private static boolean check2(String currWord, String endWord, Queue<String> queue, Queue<String> visited, Set<String> wordSet) {
        char[] charArray = currWord.toCharArray();
        // 对于currWord中的每一位，都使用26个字母替换，判断是否存在于字典以及是否和endWord相等
        for (int i = 0; i < endWord.length(); i++) {
            char temp = charArray[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == temp)
                    continue;
                charArray[i] = j;
                String nextWord = String.valueOf(charArray);
                if (wordSet.contains(nextWord)) {
                    if (nextWord.equals(endWord))
                        return true;
                    if (!visited.contains(nextWord)) {
                        queue.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            charArray[i] = temp;
        }
        return false;
    }

    // 深度优先搜索， 超时
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        if (check(beginWord, endWord) && wordList.contains(endWord))
            return 2;
        int n = wordList.size();
        boolean[] seen = new boolean[n];
        backtrack(n, 1, beginWord, endWord, seen, wordList, -1);
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    public static int res = Integer.MAX_VALUE;
    public static void backtrack(int total, int curr, String begin, String end, boolean[] seen, List<String> wordList, int index) {
        if (total < curr)
            return;
        if (index != -1 && seen[index])
            return;
        for (int i = 0; i < total; i ++) {
            if (!seen[i] && check(begin, wordList.get(i))) {
                if (end.equals(wordList.get(i))) {
                    res = Math.min(res, curr + 1);
                    return;
                }
                seen[i] = true;
                backtrack(total, curr + 1, wordList.get(i), end, seen, wordList, i);
                seen[i] = false;
            }
        }
    }
    public static boolean check(String a, String b) {
        int len = a.length();
        boolean flag = false;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (flag)
                    return false;
                flag = true;
            }
        }
        return true;
    }
}
