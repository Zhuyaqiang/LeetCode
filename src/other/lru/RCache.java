package other.lru;

import java.util.HashMap;
import java.util.Map;

public class RCache {
    int cap;
    Map<Integer, CacheNode> map;
    Map<CacheNode, Integer> map2;
    CacheNode head;
    CacheNode tail;
    public RCache(int capacity) {
        cap = capacity;
        head = new CacheNode(-1);
        tail = new CacheNode(-1);
        head.next = tail;
        tail.pre = head;
        map = new HashMap<>();
        map2 = new HashMap<>();
    }
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        CacheNode node = map.get(key);
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        node.pre.next = node.next;
        head.next.pre = node;
        node.next = head.next;
        node.pre = head;
        head.next = node;
        return node.val;
    }
    public void put(int key ,int val) {
        if (map.containsKey(key)) {
            map.get(key).val = val;
            CacheNode node = map.get(key);
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        } else {
            if (map.size() == cap) {
                CacheNode last = tail.pre;
                last.pre.next = tail;
                tail.pre = last.pre;
                map.remove(map2.get(last));
                map2.remove(last);
            }
            CacheNode node = new CacheNode(val);
            node.next = head.next;
            head.next.pre = node;
            node.pre = head;
            head.next = node;
            map.put(key, node);
            map2.put(node, key);
        }
    }
}
class CacheNode {
    int val;
    CacheNode pre;
    CacheNode next;
    public CacheNode(int val) {
        this.val = val;
    }
}
