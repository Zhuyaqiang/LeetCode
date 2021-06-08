package other;

/**
 * 相交链表
 *
 */
public class A0160 {
    public static void main(String[] args) {
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = new ListNode(8);
        headA.next.next.next = new ListNode(4);
        headA.next.next.next.next = new ListNode(5);

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);
        headB.next.next.next = headA.next.next;
        ListNode res = getIntersectionNode(headA, headB);
    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLen(headA);
        int lenB = getLen(headB);
        ListNode tempA, tempB;
        // A长
        if (lenA > lenB) {
            tempA = headA;
            tempB = headB;
        } else {
            tempA = headB;
            tempB = headA;
        }

        int diff = Math.abs(lenA - lenB);
        for (int i = 0; i < diff; i++) {
            tempA = tempA.next;
        }

        ListNode res = null;
        while (tempA != null) {
            if (tempA == tempB) {
                res = tempA;
                break;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return res;
    }
    public static int getLen(ListNode head) {
        ListNode temp = head;
        int val = 0;
        while (temp != null) {
            val++;
            temp = temp.next;
        }
        return val;
    }
}
