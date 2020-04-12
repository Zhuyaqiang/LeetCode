package array;

/**
 * 螺旋矩阵II
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 示例:
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class A0059 {
    public static void main(String[] args) {
        int[][] ints = generateMatrix(5);
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int change = 0;
        int x = 0, y = 0;
        for (int i = 0; i < n*n; i++) {
            res[x][y] = i + 1;
            int nextX = x + dx[change];
            int nextY = y + dy[change];
            if (nextX >= 0 && nextX < n && nextY >=0 && nextY < n && res[nextX][nextY] == 0) {
                x = nextX;
                y = nextY;
            } else {
                change = (change + 1) % 4;
                x = x + dx[change];
                y = y + dy[change];
            }
        }
        return res;
    }
}
