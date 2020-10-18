package other;

import java.util.HashSet;
import java.util.Set;

/**
 * 移除重复节点
 * <p>
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * 示例1:
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 * 示例2:
 * 输入：[1, 1, 1, 1, 2]
 * 输出：[1, 2]
 * 提示：
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 * 如果不得使用临时缓冲区，该怎么解决？
 */
public class M0201 {
    // 哈希表
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null)
            return head;
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode temp = head.next;
        ListNode pre = head;
        while (temp != null) {
            if (set.contains(temp.val)) {
                pre.next = temp.next;
                temp = temp.next;
            } else {
                set.add(temp.val);
                pre = temp;
                temp = temp.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode t = new ListNode(2);
        ListNode t1 = new ListNode(3);
        ListNode t2 = new ListNode(3);
        ListNode t3 = new ListNode(2);
        ListNode t4 = new ListNode(1);
        head.next = t;
        t.next = t1;
        t1.next = t2;
        t2.next = t3;
        t3.next = t4;
        display(head);
        System.out.println();
        display(removeDuplicateNodes2(head));
    }

    public static void display(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
    // 常数空间复杂度，时间换空间
    public static ListNode removeDuplicateNodes2(ListNode head) {
        if (head == null)
            return null;
        ListNode temp = head;
        while (temp != null) {
            int val = temp.val;
            ListNode node = temp.next;
            ListNode pre = temp;
            while (node != null) {
                if (node.val == val) {
                    pre.next = node.next;
                    node = node.next;
                } else {
                    pre = node;
                    node = node.next;
                }
            }
            temp = temp.next;
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }
    ListNode(int x) {
        val = x;
    }
}