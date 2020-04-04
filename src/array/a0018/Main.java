package array.a0018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 */
public class Main {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        while (nums.length < 4)
            return ans;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len-3; i++) {
            if (nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target)
                break;
            if (nums[len-1] + nums[len-2] + nums[len-3] + nums[len-4] < target)
                break;
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            for (int j = i + 1; j < len-2; j++) {
                if (j > i + 1 && nums[j] == nums[j-1])
                    continue;
                int l = j + 1, r = len - 1;
                if (nums[i] + nums[j] + nums[r] + nums[r-1] < target)
                    continue;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[r] == nums[r-1])
                            r--;
                        while (l < r && nums[l] == nums[l+1])
                            l++;
                        l++;
                        r--;
                    } else if (sum < target)
                        l++;
                    else r--;
                }
            }
        }
        return ans;
    }
}
