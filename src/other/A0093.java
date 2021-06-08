package other;

import java.util.ArrayList;
import java.util.List;

public class A0093 {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("0000"));
        System.out.println(restoreIpAddresses("1111"));
        System.out.println(restoreIpAddresses("010010"));
        System.out.println(restoreIpAddresses("101023"));
    }
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        recursion(0, s, res, new ArrayList<>());
        return res;
    }
    public static int getInt(String str) {
        int res = -1;
        if (str.length() > 1 && str.charAt(0) == '0') {
            return -1;
        }
        try {
            res = Integer.parseInt(str);
        } catch (Exception ignored) {
        }
        return res >= 0 && res <= 255 ? res : -1;
    }
    public static void recursion(int index, String s, List<String> res, List<String> curr) {
        if (curr.size() == 4) {
            if (index == s.length()) {
                res.add(String.join(".", curr));
            }
            return;
        }
        if (index >= s.length()) {
            return;
        }
        int pre = 0;
        for (int i = index + 1; i <= s.length(); i++) {
            int val = getInt(s.substring(index, i));
            if (i == index + 1) {
                pre = val;
            } else {
                if (pre == val) {
                    continue;
                }
            }
            if (val != -1) {
                curr.add(val + "");
                recursion(i, s, res, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }
}
