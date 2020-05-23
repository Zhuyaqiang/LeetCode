package array;

import java.util.*;

/**
 * 我的日程安排
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 * 请按照以下步骤调用 MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * 示例 1:
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * 解释:
 * 第一个日程安排可以添加到日历中.  第二个日程安排不能添加到日历中，因为时间 15 已经被第一个日程安排预定了。
 * 第三个日程安排可以添加到日历中，因为第一个日程安排并不包含时间 20 。
 * 说明:
 * 每个测试用例，调用 MyCalendar.book 函数最多不超过 100次。
 * 调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。
 */
public class A0729 {
    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();
        System.out.println(calendar.book(47, 50));
        System.out.println(calendar.book(33, 41));
        System.out.println(calendar.book(39, 45));
        System.out.println(calendar.book(33, 42));
        System.out.println(calendar.book(25, 32));
        System.out.println(calendar.book(26, 35));
        System.out.println(calendar.book(19, 25));
        System.out.println(calendar.book(3, 8));
        System.out.println(calendar.book(8, 13));
        for (int[] ints : calendar.list) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println(calendar.book(18, 27));
    }

}
class MyCalendar {
    public LinkedList<int[]> list;
    public MyCalendar() {
        list = new LinkedList<>();
    }

    public boolean book(int start, int end) {
        if (list.size() == 0) {
            list.add(new int[]{start, end - 1});
            return true;
        }
        int index = 0;
        for (int[] ints : list) {
            if (ints[0] == start)
                return false;
            if (ints[0] > start) {
                if (end -1 >= ints[0])
                    return false;
                break;
            }
            if (ints[1] >= start)
                return false;
            index ++;
        }
        if (index == list.size())
            list.add(new int[]{start, end-1});
        else {
            list.add(index, new int[] {start, end-1});
        }
        Collections.sort(list, (o1, o2) -> {
            if (o1[0] == o2[0])
                return 0;
            else if (o1[0] > o2[0])
                return 1;
            else
                return -1;
        });
        return true;
    }
}

// 二叉树结构
class MyCalendar2 {
    TreeMap<Integer, Integer> calendar;

    MyCalendar2() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start), // 返回小于等于start的最大键
                next = calendar.ceilingKey(start); // 返回大于等于start的最小键
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}