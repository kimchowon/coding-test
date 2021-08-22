package programmers.level2;

import java.util.HashSet;

/**
 * 소수 찾기
 */
public class Test08 {
    public static int count = 0;
    public static HashSet<Integer> set;

    public static void main(String[] args) {

        String numbers = "17";
        System.out.println(solution(numbers));
    }

    public static int solution(String numbers) {
        set = new HashSet<>();
        char[] number_chars = numbers.toCharArray();

        /**
         * 한자리 부터 주어진 배열 크기 개수의 자리수까지 만들 수 있는 모든 수 구하기.
         */
        for (int i = 0; i < number_chars.length; i++) {
            int[] buckets = new int[i + 1];
            recursion(number_chars, buckets, buckets.length);
        }

        /**
         * 소수 여부 판별
         */
        for (int n : set) {
            if (n == 0 || n == 1) {
                continue;
            }
            boolean flag = true;
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }

    /**
     * 순열
     * @param numbers
     * @param buckets
     * @param k
     */
    public static void recursion(char[] numbers, int[] buckets, int k) {
        if (k == 0) {
            String numStr = "";
            for (int i = 0; i < buckets.length; i++) {
                numStr += String.valueOf(numbers[buckets[i]]);
            }
            set.add(Integer.parseInt(numStr));
            return;
        }

        int lastIndex = buckets.length - k - 1;
        for (int i = 0; i < numbers.length; i++) {
            boolean flag = false;

            for (int j = 0; j <= lastIndex; j++) {
                if (buckets[j] == i) {
                    flag = true;
                }
            }

            if (flag) {
                continue;
            }

            buckets[lastIndex + 1] = i;
            recursion(numbers, buckets, k - 1);
        }
    }
}
