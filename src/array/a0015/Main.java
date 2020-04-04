package array.a0015;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = {0};
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3)
            return res;
        Arrays.sort(nums);
        int len = nums.length;
        int l, r;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            if (nums[i] > 0)
                return res;
            l = i + 1;
            r = len - 1;
            if (nums[i] + nums[r] + nums[r-1] < 0)
                continue;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l+1]) l++;
                    while (l < r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                } else if (sum > 0)
                    r--;
                else
                    l++;
            }
        }
        return res;
    }
}
