import java.util.HashMap;

public class ExRemoveElement {
    public int removeElement(int[] nums, int val) {
        int i, j = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                //target
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}
