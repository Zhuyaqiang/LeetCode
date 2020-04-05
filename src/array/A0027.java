package array;

public class A0027 {
    public static void main(String[] args) {
        int nums[] = {0,1,2,2,3,0,4,2};
        int res = removeElement3(nums, 2);
        System.out.println(res);
        for (int i = 0; i < res; i++) {
            System.out.print(nums[i]);
        }
    }
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0)
            return 0;
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                int k = i;
                for (int j = k + 1; j < nums.length; j++) {
                    if (nums[j] != val) {
                        nums[k] = nums[j];
                        k++;
                    }
                }
                ans = k;
                break;
            }
        }
        return ans;
    }

    public static int removeElement2(int[] nums, int val) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[ans] = nums[i];
                ans++;
            }
        }
        return ans;
    }

    public static int removeElement3(int[] nums, int val) {
        int ans = nums.length;
        int i = 0;
        while (i < ans) {
            if (nums[i] == val) {
                nums[i] = nums[ans-1];
                ans--;
            } else {
                i++;
            }
        }
        return ans;
    }
}
