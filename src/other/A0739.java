package other;

import java.util.Stack;

/**
 * 每日气温
 *
 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。

 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class A0739 {
    public static void main(String[] args) {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints = dailyTemperatures2(T);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
    // 暴力法
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        for (int i = 0; i < len; i++) {
            boolean flag = false;
            for (int j = i + 1; j < len; j++) {
                if (T[j] > T[i]) {
                    T[i] = j - i;
                    flag = true;
                    break;
                }
            }
            if (!flag)
                T[i] = 0;
        }
        return T;
    }
    // 单调栈
    public static int[] dailyTemperatures2(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }
}
