package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Test13 {
    public static void main(String[] args) {
        String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";

        System.out.println(Arrays.toString(solution(s)));
    }

    public static int[] solution(String s) {
        String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
        Arrays.sort(arr, Comparator.comparingInt(o -> o.length()));
        int[] answer = new int[arr.length];

        int index = 0;
        for (String a : arr) {
            String[] a_arr = a.split(",");
            for (int i = 0; i < a_arr.length; i++) {
                int finalI = i;
                if (IntStream.of(answer).anyMatch(x -> x == Integer.parseInt(a_arr[finalI]))) {
                    continue;
                }
                answer[index++] = Integer.parseInt(a_arr[finalI]);
            }
        }

        return answer;
    }
}
