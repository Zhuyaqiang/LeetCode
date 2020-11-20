package other;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**

给定两个数组，编写一个函数来计算它们的交集。

示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]
说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
我们可以不考虑输出结果的顺序。
进阶:

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
**/
public class A0350 {
    public static void main(String[] args) {
    }
    // 哈希表
	public int[] intersect(int[] nums1, int[] nums2) {
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
    	List<Integer> res = new ArrayList<>();
		for (int i : nums1) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}

		for (int i : nums2) {
			if (map.containsKey(i)) {
				list.add(i);
				int count = map.get(i);
				count --;
				if (count == 0)
					map.remove(i);
				else
					map.put(i, count);
			}
		}
		int[] ret = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			ret[i] = list.get(i);
		return ret;
	}
	// 排序
	public int[] intersect2(int[] nums1, int[] nums2) {
    	Arrays.sort(nums1);
    	Arrays.sort(nums2);
    	int len1 = nums1.length, len2 = nums2.length;
    	int[] res = new int[len1];
    	int index = 0, oneIndex = 0, twoIndex = 0;
    	while (oneIndex < len1 && twoIndex < len2) {
    		if (nums1[oneIndex] == nums2[twoIndex]) {
    			res[index ++] = nums1[oneIndex];
    			oneIndex ++;
    			twoIndex ++;
			} else if (nums1[oneIndex] < nums2[twoIndex]) {
    			oneIndex ++;
			} else
				twoIndex ++;
		}
    	return Arrays.copyOfRange(res, 0, index);
	}
}
