package programmers.level2;

import java.util.Arrays;
import java.util.stream.Stream;

public class Test18 {
    public static void main(String[] args) {
        String s = "1 2 3 4";
        System.out.println(solution(s));

    }

    public static String solution(String s) {
        String answer = "";

        int[] nums = Stream.of(s.split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(nums);

        answer = nums[0] + " " + nums[nums.length-1];
        return answer;
    }
}
