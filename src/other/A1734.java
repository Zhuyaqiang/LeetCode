package other;

public class A1734 {
    public static void main(String[] args) {
        int[] encoded = {6,5,4,6};
        System.out.println(decode(encoded));
    }
    public static int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int all = 1;
        for (int i = 2; i <= n; i++) {
            all ^= i;
        }
        int temp = encoded[1];
        for (int i = 3; i < n - 1; i += 2) {
            temp ^= encoded[i];
        }
        int first = temp ^ all;
        System.out.println(n);
        int[] res = new int[n];
        res[0] = first;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] ^ encoded[i - 1];
        }
        return res;
    }
}
