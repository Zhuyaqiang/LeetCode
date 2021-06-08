package other;

import java.util.*;

/**
 * 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * 注意：
 * 总人数少于1100人。
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class A0406 {
        // 根据身高降序, k升序排, 因为k只与在它前方比他高的人有关, 从左往右插入, 不需要考虑后面比它矮的人, 插入时只需要考虑前面保证有k个人即可(person[1])
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        if (len <= 1)
            return people;

        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0])
                return o2[0] - o1[0];
            else
                return o1[1] - o2[1];
        });
        List<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0, 1);
        System.out.println(list);
        list.add(0, 2);
        System.out.println(list);
        list.add(0, 3);
        System.out.println(list);
    }
}
