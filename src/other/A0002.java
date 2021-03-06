package other;

/**
 * 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class A0002 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        rAddTwoNumbers(l1, l2);
    }
    public static ListNode rAddTwoNumbers(ListNode l1, ListNode l2) {
        int oneLen = 0, twoLen = 0;
        ListNode one = l1, two = l2;
        while (one != null) {
            oneLen++;
            one = one.next;
        }
        while (two != null) {
            twoLen++;
            two = two.next;
        }
        // one 长 two 短
        if (oneLen < twoLen) {
            one = l2;
            two = l1;
        } else {
            one = l1;
            two = l2;
        }
        int c = 0;
        l1 = one;
        l2 = two;
        while (two != null) {
            int val = one.val + two.val + c;
            c = val / 10;
            one.val = val % 10;
            if (one.next == null) {
                if (c == 1) {
                    one.next = new ListNode(1);
                }
                return l1;
            }
            one = one.next;
            two = two.next;
        }
        while (one.next != null) {
            one.val += c;
            c = one.val / 10;
            if (c == 0) {
                return l1;
            }
            one.val = one.val % 10;
            one = one.next;
        }
        one.val += c;
        if (one.val == 10) {
            one.val = 0;
            one.next = new ListNode(1);
        }
        return l1;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode l1Temp = l1, l2Temp = l2, l1Pre = l1, l2Pre = l2;
        int c = 0;
        while (l1Temp != null && l2Temp != null) {
            int val = l1Temp.val + l2Temp.val + c;
            c = val / 10;
            val = val % 10;
            l1Temp.val = val;
            l2Temp.val = val;
            l1Pre = l1Temp;
            l2Pre = l2Temp;

            l1Temp = l1Temp.next;
            l2Temp = l2Temp.next;
        }
        if (l1Temp == null && l2Temp == null) {
            if (c == 0) {
                return l1;
            } else {
                l1Temp = new ListNode(1);
                l1Pre.next = l1Temp;
                return l1;
            }
        } else if (l1Temp == null) {
            if (c == 0) {
                return l2;
            } else {
                while (l2Temp != null) {
                    int val = l2Temp.val + c;
                    l2Temp.val = val % 10;
                    c = val / 10;
                    l2Pre = l2Temp;
                    l2Temp = l2Temp.next;
                }
                if (c > 0) {
                    l2Pre.next = new ListNode(1);
                }
                return l2;
            }
        } else {
            if (c == 0) {
                return l1;
            } else {
                while (l1Temp != null) {
                    int val = l1Temp.val + c;
                    l1Temp.val = val % 10;
                    c = val / 10;
                    l1Pre = l1Temp;
                    l1Temp = l1Temp.next;
                }
                if (c > 0) {
                    l1Pre.next = new ListNode(1);
                }
                return l1;
            }
        }
    }
}
