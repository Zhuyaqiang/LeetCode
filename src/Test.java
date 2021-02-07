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
        LocalDateTime localTime = LocalDateTime.parse("2021-01-01 24:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localTime2 = LocalDateTime.parse("2021-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(localTime);
        System.out.println(localTime2);
    }
}
