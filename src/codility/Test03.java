package codility;

import java.util.*;

/**
 * Lesson2.Arrays - OddOccurrencesInArray
 */
public class Test03 {
    public static void main(String[] args) {
        int[] A = {9};
        int answer = solution(A);
        System.out.println(answer);
    }

    public static int solution(int[] A) {
        int answer = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i=0; i < A.length; i++) {
            countMap.put(A[i], countMap.getOrDefault(A[i], 0) + 1);
        }

        for (int key : countMap.keySet()) {
            int value = countMap.get(key);
            if (value % 2 != 0) {
                answer = key;
                break;
            }
        }
        return answer;
    }
}
