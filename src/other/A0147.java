package other;

/**
 * 对链表进行插入排序。
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *  
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class A0147 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        insertionSortList(head);
    }
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode temp = head.next;
        ListNode pre = head;
        ListNode curr = head;
        while (temp != null) {
            ListNode nextTemp = temp.next;
            pre.next = nextTemp;
            if (temp.val <= head.val) {
                temp.next = head;
                head = temp;
            } else {
                while (curr.next != null && curr.next.val <= temp.val)
                    curr = curr.next;
                // curr.next == null or curr.next.val > temp.val
                ListNode currNext = curr.next;
                curr.next = temp;
                temp.next = currNext;
            }
            curr  = head;
            if (temp.next == nextTemp)
                pre = temp;
            temp = nextTemp;
        }
        return head;
    }

    public static ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val)
                lastSorted = lastSorted.next;
            else {
                ListNode pre = dummyHead;
                while (pre.next.val <= curr.val)
                    pre = pre.next;
                // curr 插在 pre 和 pre.next 之间
                lastSorted.next = curr.next;
                curr.next = pre.next;
                pre.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }
}
