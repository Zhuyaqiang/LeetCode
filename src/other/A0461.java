package other;

/**
 * 汉明距离
 */
public class A0461 {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1577962638, 1727613287));
    }
    public static int hammingDistance(int x, int y) {
        int z = x ^ y;
        int res = 0;
        for (int i = 0; i <= 31; i++) {
            if ((z & 1 << i) != 0) {
                res++;
            }
        }
        return res;
    }
}
