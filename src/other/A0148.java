package other;

/**
 * 排序链表
 * <p>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 */
public class A0148 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        ListNode listNode = rSortList(head);
        System.out.println(listNode);
    }

    public static ListNode rSortList(ListNode head) {
        return rMergeSort(head, null);
    }

    private static ListNode rMergeSort(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode head1 = rMergeSort(head, mid);
        ListNode head2 = rMergeSort(mid, tail);
        ListNode merge = rMerge(head1, head2);
        return merge;
    }

    private static ListNode rMerge(ListNode head1, ListNode head2) {
        ListNode temp1 = head1, temp2 = head2;
        ListNode pre = new ListNode(0), temp = pre;
        while (temp1 != null || temp2 != null) {
            if (temp1 == null) {
                temp.next = temp2;
                temp2 = temp2.next;
                temp = temp.next;
            } else if (temp2 == null) {
                temp.next = temp1;
                temp1 = temp1.next;
                temp = temp.next;
            } else {
                if (temp1.val < temp2.val) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
                temp = temp.next;
            }
        }
        return pre.next;
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        ListNode sortedTail = head, unSortedHead = head.next;
        while (unSortedHead != null) {
            if (sortedTail.val <= unSortedHead.val) {
                sortedTail = sortedTail.next;
            } else {
                ListNode pre = dummyHead;
                while (pre.next.val <= unSortedHead.val) {
                    pre = pre.next;
                }
                // unsortedhead插在 pre 和 pre.next之间
                sortedTail.next = unSortedHead.next;
                unSortedHead.next = pre.next;
                pre.next = unSortedHead;
            }
            unSortedHead = sortedTail.next;
        }
        return dummyHead.next;
    }

    // 归并排序
    public static ListNode sortList2(ListNode head) {
        return mergeSort(head, null);
    }

    public static ListNode mergeSort(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode head1 = mergeSort(head, mid);
        ListNode head2 = mergeSort(mid, tail);
        ListNode sorted = merge(head1, head2);
        return sorted;
    }

    private static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        }
        if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}
