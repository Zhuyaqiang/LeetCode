package array;

/**
 * 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 */
public class A0074 {
    public static void main(String[] args) {
        int[][] matrix = {{}};
        System.out.println(searchMatrix2(matrix, 3));
    }

    // 二分查找, 先找到对应行, 再找对应列
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0)
            return false;
        if (matrix[0] == null || matrix[0].length == 0)
            return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0, end = m - 1;
        int index = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target >= matrix[mid][0] && target <= matrix[mid][n - 1]) {
                index = mid;
                break;
            } else if (target < matrix[mid][0])
                end = mid - 1;
            else if (target > matrix[mid][n - 1])
                start = mid + 1;
        }
        if (index == -1)
            return false;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (matrix[index][mid] == target)
                return true;
            else if (matrix[index][mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return false;
    }
    // 二分查找, 直接查
    public static boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0)
            return false;
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1;
        int num;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            num = matrix[mid / n][mid % n];
            if (num == target)
                return true;
            else if (num > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return false;
    }
}
