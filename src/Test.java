/**
 * @author zhuyaqiang <zhuyaqiang@kuaishou.com>
 * Created on 2020-12-18
 */
public class Test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("ll");
        change(sb);
        System.out.println(sb.toString());
    }

    public static void change(StringBuilder sb) {
        sb.append("24234");
    }
}
