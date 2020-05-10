package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 输入: 5
 * 输出:
 * [
 *  [1],
 *  [1,1],
 *  [1,2,1],
 *  [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class A0118 {
    public static void main(String[] args) {
        List<List<Integer>> lists = generate(5);
        System.out.println(lists);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<Integer> pre = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i; j++) {
                list.add(pre.get(j - 1) + pre.get(j));
            }
            if (i != 0)
                list.add(1);
            pre = list;
            res.add(new ArrayList<>(pre));
        }
        return res;
    }
}
