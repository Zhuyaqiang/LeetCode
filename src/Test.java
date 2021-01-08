import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author zhuyaqiang <zhuyaqiang@kuaishou.com>
 * Created on 2020-12-18
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(null);
        System.out.println(arrayList.get(0));
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(null);
        System.out.println(linkedList.get(0));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        System.out.println(map.get(2));
    }
}
