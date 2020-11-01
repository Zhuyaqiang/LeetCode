package other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 填充每个节点的下一个右侧节点指针2
 * 给定一个二叉树
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * 示例：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 */
public class A0117 {
    public static void main(String[] args) {
//        Node node = new Node(1);
//        node.left = new Node(2);
//        node.left.left = new Node(4);
//        node.left.right = new Node(5);
//        node.right = new Node(3);
//        node.right.right = new Node(7);
        Node node = new Node(1);
        node.left = new Node(2);
        Node connect = connect2(node);
    }

    // 广度优先搜索
    public static Node connect(Node root) {
        Deque<Node> deque = new ArrayDeque<>();
        if (root != null)
            deque.offer(root);
        while (!deque.isEmpty()) {
            Node pre = deque.poll();
            int n = deque.size();
            if (pre.left != null)
                deque.offer(pre.left);
            if (pre.right != null)
                deque.offer(pre.right);
            pre.next = null;
            if (n == 0)
                continue;
            for (int i = 0; i < n; i++) {
                Node curr = deque.poll();
                if (curr == null)
                    continue;
                if (curr.left != null)
                    deque.offer(curr.left);
                if (curr.right != null)
                    deque.offer(curr.right);
                pre.next = curr;
                pre = curr;
            }
        }
        return root;

    }

    public static Node connect2(Node root) {
        if (root == null)
            return null;
        Node pre = root;
        Node head;
        if (pre.left != null)
            head = pre.left;
        else if (pre.right != null)
            head = pre.right;
        else
            return root;
        Node temp;
        Node tempPre;
        while (head != null) {
            temp = head;
            tempPre = head;
            while (pre != null) {
                if (pre.left != null && pre.left != temp) {
                    temp.next = pre.left;
                    temp = temp.next;
                }
                if (pre.right != null && pre.right != temp) {
                    temp.next = pre.right;
                    temp = temp.next;
                }
                pre = pre.next;
            }
            pre = tempPre;
            head = null;
            while (tempPre != null) {
                if (tempPre.left != null) {
                    head = tempPre.left;
                    break;
                }
                if (tempPre.right != null) {
                    head = tempPre.right;
                    break;
                }
                tempPre = tempPre.next;
            }
        }
        return root;
    }


    // 官方题解
    // 在t层为t+1层建立next关系
    Node last = null, nextStart = null;
    public Node connect3(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left);
                }
                if (p.right != null) {
                    handle(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    public void handle(Node p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-15/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        Node(int x) {
            val = x;
        }
    }
}

