package other;

import java.util.*;

/**
 * @author zhuyaqiang <zhuyaqiang@kuaishou.com>
 * Created on 2020-12-14
 * 字母异位单词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class A0049 {
    public static List<List<String>> rGroupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = rGetKey(str);
            List<String> val = map.get(key);
            if (val == null) {
                val = new ArrayList<>();
            }
            val.add(str);
            map.put(key, val);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            res.add(value);
        }
        return res;
    }
    public static String rGetKey(String str) {
        int[] count = new int[26];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'a']++;
        }
        return Arrays.toString(count);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] temp = new int[26];
            for (int i = 0; i < str.length(); i++) {
                temp[str.charAt(i) - 'a']++;
            }
            String key = Arrays.toString(temp);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
}
