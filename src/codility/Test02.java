package codility;

import java.util.Arrays;

/**
 * Lesson2.Arrays - CyclicRotation
 */
public class Test02 {
    public static void main(String[] args) {
        int[] A = {};
        int K = 3;
        int[] answer = solution(A, K);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(int[] A, int K) {
        int len = A.length;
        if (len == 0) {
            return A;
        }
        for (int i = 1; i <= K; i++) {
            int[] temp = new int[len];
            for (int j = 0; j < len - 1; j++) {
                temp[j + 1] = A[j];
            }
            temp[0] = A[len - 1];
            A = temp;
        }
        return A;
    }
}
