package other;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 示例：
 给定一个链表: 1->2->3->4->5, 和 n = 2.
 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 说明：
 给定的 n 保证是有效的。
 进阶：
 你能尝试使用一趟扫描实现吗？
 */
public class A0019 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head = removeNthFromEnd3(head,2);
        display(head);
    }
    // 两趟扫描
    public static  ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count ++;
            temp = temp.next;
        }
        if (count == n)
            return head.next;
        int now = count - n;
        temp = head;
        while (now > 1) {
            temp = temp.next;
            now --;
        }
        if (n == 1) {
            temp.next = null;
        } else {
            temp.next = temp.next.next;
        }
        return head;
    }
    public static void display(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
    // 一趟扫描
    public static  ListNode removeNthFromEnd2(ListNode head, int n) {
        Map<Integer, ListNode> map = new HashMap<>();
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count ++;
            map.put(count, temp);
            temp = temp.next;
        }
        if (count == n)
            return head.next;
        if (n == 1) {
            map.get(count - n).next = null;
        } else {
            map.get(count - n).next = map.get(count - n).next.next;
        }
        return head;
    }

    // 快慢指针
    public static ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode quick = head, slow = new ListNode(0, head);
        for (int i = 1; i < n; i++) {
            quick = quick.next;
        }
        if (quick.next == null)
            return head.next;
        while (quick.next != null) {
            quick = quick.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
