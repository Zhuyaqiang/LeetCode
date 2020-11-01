package other;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 填充每个节点的下一个右侧节点指针
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class A0116 {
    // 广度优先搜索
    public Node connect(Node root) {
        if (root == null)
            return null;
        Deque<Node> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            for (int i = 0; i < n; i++) {
                Node temp = deque.poll();
                if (i < n - 1)
                    temp.next = deque.peek();
                if (temp.left != null)
                    deque.offer(temp.left);
                if (temp.right != null)
                    deque.offer(temp.right);
            }
        }
        return root;
    }

    // 利用完美二叉树的性质, 每个父节点都有两个子结点
    public Node connect2(Node root) {
        if (root == null)
            return null;
        Node leftMost = root;
        while (leftMost.left != null) {
            Node head = leftMost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null)
                    head.right.next = head.next.left;
                head = head.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
