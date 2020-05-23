package array;

/**
 * 托普利茨矩阵
 *
 如果一个矩阵的每一方向由左上到右下的对角线上具有相同元素，那么这个矩阵是托普利茨矩阵。
 给定一个 M x N 的矩阵，当且仅当它是托普利茨矩阵时返回 True。
 示例 1:
 输入:
 matrix = [
 [1,2,3,4],
 [5,1,2,3],
 [9,5,1,2]
 ]
 输出: True
 解释:
 在上述矩阵中, 其对角线为:
 "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 各条对角线上的所有元素均相同, 因此答案是True。
 示例 2:
 输入:
 matrix = [
 [1,2],
 [2,2]
 ]
 输出: False
 解释:
 对角线"[1, 2]"上的元素不同。
 说明:
 matrix 是一个包含整数的二维数组。
 matrix 的行数和列数均在 [1, 20]范围内。
 matrix[i][j] 包含的整数在 [0, 99]范围内。
 进阶:
 */
public class A0766 {
    public static void main(String[] args) {
//        int[][] matrix = {
//  {1,2,3,4},
//  {5,1,2,3},
//  {9,5,1,2}
//};
        int[][] matrix = {{41,45},{81,41},{73,81},{47,73},{76,47},{79,76}};
        System.out.println(isToeplitzMatrix2(matrix));
    }
    public static boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 1)
            return true;
        int n = matrix[0].length;
        if (n == 1)
            return true;
        for (int i = 0; i < n; i++) {
            int i1 = 0, j1 = i;
            int pivot = matrix[0][i];
            while (i1 < m && j1 < n) {
                if (matrix[i1][j1] != pivot)
                    return false;
                i1++;
                j1++;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < i && j < n; j++) {
                int pivot = matrix[i][j];
                int i1 = i, j1 = j;
                while (i1 < m && j1 < n) {
                    if (matrix[i1][j1] != pivot)
                        return false;
                    i1++;
                    j1++;
                }
            }
        }
        return true;
    }
    public static boolean isToeplitzMatrix2(int[][] matrix) {
        int m = matrix.length;
        if (m == 1)
            return true;
        int n = matrix[0].length;
        if (n == 1)
            return true;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i-1][j-1])
                    return false;
            }
        }
        return true;
    }
}
