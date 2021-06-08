package other.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Node head;
    Node tail;
    Map<Integer, Node> map;
    Map<Node, Integer> map2;
    int cap;
    public LRUCache(int capacity) {
        cap = capacity;
        map = new HashMap<>();
        map2 = new HashMap<>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            refresh(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            refresh(node);
        } else {
            Node node = new Node(value);
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
            map.put(key, node);
            map2.put(node, key);
        }
        if (map.size() > cap) {
            Node node = tail.pre;
            tail.pre = node.pre;
            node.pre.next = tail;
            int key2 = map2.get(node);
            map.remove(key2);
            map2.remove(node);
        }
    }

    // 把node放到最前面
    public void refresh(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }
}
class Node {
    int val;
    Node pre;
    Node next;
    public Node(int val) {
        this.val = val;
    }
    public Node(int val, Node pre, Node next) {
        this.val = val;
        this.pre = pre;
        this.next = next;
    }
}