package other;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *  
 *
 * 示例:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *  
 *
 * 提示：
 *
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 */
public class A0155 {

    public static void main(String[] args) {
        A0155 a0155 = new A0155();
        a0155.push(-2);
        a0155.push(0);
        a0155.push(-3);
        a0155.getMin();
        a0155.pop();
        a0155.top();
        a0155.getMin();
    }
    // 辅助栈
    public A0155() {
        min.push(Integer.MAX_VALUE);
    }
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> min = new Stack<>();
    public void push(int x) {
        stack1.push(x);
        min.push(Math.min(x, min.peek()));
    }

    public void pop() {
        min.pop();
        stack1.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
