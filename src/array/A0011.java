package array;

public class A0011 {
    public static void main(String[] args) {

    }
    public static int Solution1(int[] height) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int h = height[i] > height[j] ? height[j] : height[i];
                int area = h * (j-i);
                if (area > res)
                    res = area;
            }
        }
        return res;
    }
    public static int Solution2(int[] height) {
        int start = 0, end = height.length-1;
        int res = Integer.MIN_VALUE;
        while (start < end) {
            int h = Math.min(height[start], height[end]);
            res = Math.max(res, h * (end-start));
            if (height[start] > height[end])
                end--;
            else
                start++;
        }
        return res;
    }
}
