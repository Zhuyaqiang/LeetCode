package other;

import java.util.concurrent.locks.StampedLock;

/**
 * K个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 示例 3：
 *
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * 示例 4：
 *
 * 输入：head = [1], k = 1
 * 输出：[1]
 * 提示：
 *
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */
public class A0025 {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        ListNode listNode = rReverseKGroup(node, 3);
        display(listNode);
    }

    public static ListNode rReverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(-1), pre = hair, temp = head;
        hair.next = head;
        while (temp != null) {
            ListNode tempHead = temp;
            int index = 0;
            for (; index < k - 1 && temp != null; index++) {
                temp = temp.next;
            }
            if (temp == null) {
                break;
            }
            ListNode next = temp.next;
            temp.next = null;
            reverse(tempHead);
            pre.next = temp;
            tempHead.next = next;
            pre = tempHead;
            temp = next;
        }
        return hair.next;
    }
    public static void rReverse(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode pre = null, next = head.next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }


    public static void display(ListNode node) {
        ListNode temp = node;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead, tempHead = head, tail = head, next;
        do {
            for (int i = 0; i < k - 1 && tail != null; i++) {
                tail = tail.next;
            }
            if (tail != null) {
                next = tail.next;
                tail.next = null;
                reverse(tempHead);
                pre.next = tail;
                tempHead.next = next;
                pre = tempHead;
                tempHead = tempHead.next;
                tail = tempHead;
            }
        } while (tail != null);
        return dummyHead.next;
    }
    public static ListNode reverse(ListNode node) {
        ListNode pre = null, temp = node, next = temp.next;
        while (temp != null) {
            next = temp.next;
            temp.next = pre;
            pre = temp;
            temp = next;
        }
        return pre;
    }
}
