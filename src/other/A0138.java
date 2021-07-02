package other;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制带随机指针的链表
 */
public class A0138 {
    public static void main(String[] args) {
        Node1 node = new Node1(7);
        Node1 node1 = new Node1(13);
        Node1 node2 = new Node1(11);
        Node1 node3 = new Node1(10);
        Node1 node4 = new Node1(1);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node.random = null;
        node1.random = node;
        node2.random = node4;
        node3.random = node2;
        node4.random = node;
        copyRandomList(node);
    }
    public static Node1 copyRandomList(Node1 head) {
        if (head == null) {
            return head;
        }
        Node1 newNode = recursion(head, new HashMap<>());
        return newNode;
    }
    public static Node1 recursion(Node1 head, Map<Node1, Node1> next) {
        if (head == null) {
            return null;
        }
        if (next.containsKey(head)) {
            return next.get(head);
        }
        Node1 Node1 = new Node1(head.val);
        next.put(head, Node1);
        Node1.next = recursion(head.next, next);
        Node1.random = recursion(head.random, next);
        StringBuilder sb = new StringBuilder();
        return Node1;
    }
}
class Node1 {
    int val;
    Node1 next;
    Node1 random;

    public Node1(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}