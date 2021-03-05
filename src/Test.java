import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author zhuyaqiang <zhuyaqiang@kuaishou.com>
 * Created on 2020-12-18
 */
public class Test {
    public static void main(String[] args) {
        int i = 1;
        System.out.println(i);
        i = i++;
        System.out.println(i);
        i = ++i;
        System.out.println(i);
        int j = i++;
        System.out.println(j);
        int k = ++j;
        System.out.println(k);
        int m = i++ * ++i;
        System.out.println(i);
        System.out.println("m: " + m);
    }
}
