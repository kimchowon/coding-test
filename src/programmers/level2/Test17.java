package programmers.level2;


import java.util.HashSet;
import java.util.Set;

public class Test17 {
    public static void main(String[] args) {

        int[] nums /*= {3,3,3,2,2,2};*/= {3,3,3,2,2,4};
        System.out.println(solution(nums));
    }

    public static int solution(int[] nums) {
        int N = nums.length;
        Set<Integer> set = new HashSet<>();

        for (int i=0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int set_length = set.size();
        while (set_length > N/2) {
            set_length--;
        }

        return set_length;
    }
}
