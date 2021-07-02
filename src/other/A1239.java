package other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class A1239 {
    public static void main(String[] args) {
        List<String> arr = Arrays.asList("un", "iq", "ue");
        System.out.println(maxLength(arr));
        List<String> arr2 = Arrays.asList("cha","r","act","ers");
        System.out.println(maxLength(arr2));
        List<String> arr3 = Arrays.asList("abcdefghijklmnopqrstuvwxyz");
        System.out.println(maxLength(arr3));
    }
    static String res = "";
    public static int maxLength(List<String> arr) {
        recursion(new StringBuilder(), new HashSet<>(), arr, 0);
        return res.length();
    }
    public static void recursion(StringBuilder sb, Set<Character> curr, List<String> arr, int index) {
        if (index ==  arr.size()) {
            if (sb.length() >= res.length()) {
                res = sb.toString();
            }
            return;
        }
        recursion(sb, curr, arr, index + 1);
        String str = arr.get(index);
        Set<Character> count = getCount(str);
        if (count == null) {
            return;
        }
        Set<Character> temp = new HashSet<>();
        for (Character character : count) {
            if (curr.contains(character)) {
                for (Character character1 : temp) {
                    curr.remove(character1);
                }
                return;
            }
            temp.add(character);
            curr.add(character);
        }
        int len = sb.length();
        recursion(sb.append(str), curr, arr, index + 1);
        for (Character character1 : temp) {
            curr.remove(character1);
        }
        sb.delete(len, sb.length());
    }
    public static Set<Character> getCount(String str) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (set.contains(ch)) {
                return null;
            }
            set.add(ch);
        }
        return set;
    }
}
