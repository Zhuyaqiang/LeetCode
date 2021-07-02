package other.lfu;

import java.util.*;

public class LFUCache {
    Node head;
    Node tail;
    Map<Integer, Node> map;
    Map<Node, Integer> map2;
    PriorityQueue<Node> priorityQueue;
    Map<Integer, Integer> counter;
    int cap;
    Stack<Node> stack;
    public LFUCache(int capacity) {
        cap = capacity;
        map = new HashMap<>();
        map2 = new HashMap<>();
        head = new Node(-1);
        tail = new Node(-1);
        counter = new HashMap<>();
        stack = new Stack<>();
        priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return counter.getOrDefault(o1, 0) - counter.getOrDefault(o2, 0);
            }
        });
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            refresh(node);
            counter.put(key, counter.getOrDefault(node, 0) + 1);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cap == 0) {
            return;
        }
        if (!map.containsKey(key) && map.size() == cap) {
            Node temp = priorityQueue.peek();
            int num = counter.get(map2.get(temp));
            while (!priorityQueue.isEmpty() && counter.get(map2.get(priorityQueue.peek())) == num) {
                stack.push(priorityQueue.poll());
            }

            Node nodeDelete;
            if (stack.size() == 1) {
                nodeDelete = stack.pop();
            } else {
                Set<Node> set = new HashSet<>();
                while (!stack.isEmpty()) {
                    set.add(stack.pop());
                }
                nodeDelete = tail;
                while (!set.contains(nodeDelete)) {
                    nodeDelete = nodeDelete.pre;
                }
                for (Node node : set) {
                    if (node != nodeDelete) {
                        priorityQueue.offer(node);
                    }
                }
            }

            nodeDelete.pre.next = nodeDelete.next;
            nodeDelete.next.pre = nodeDelete.pre;
            int key2 = map2.get(nodeDelete);
            map.remove(key2);
            map2.remove(nodeDelete);
            counter.put(key2, 0);
        }
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            refresh(node);
            counter.put(key, counter.getOrDefault(node, 0) + 1);
        } else {
            Node node = new Node(value);
            priorityQueue.offer(node);
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
            map.put(key, node);
            map2.put(node, key);
            counter.put(key, counter.getOrDefault(node, 0) + 1);
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
