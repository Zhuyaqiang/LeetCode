package other;

import java.util.HashSet;
import java.util.Set;

/**
 * 宝石与石头
 */
public class A0771 {
    public int numJewelsInStones(String J, String S) {
        char[] charsJ = J.toCharArray();
        char[] chasS = S.toCharArray();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < charsJ.length; i++) {
            set.add(charsJ[i]);
        }
        int ans = 0;
        for (int i = 0; i < chasS.length; i++) {
            if (set.contains(chasS[i]))
                ans ++;
        }
        return ans;
    }
}
