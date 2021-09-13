package kakao.blind2022;

import java.util.*;

public class Test04 {
    public static void main(String[] args) {
       /* int n = 5;
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};*/

       /* int n = 1;
        int[] info = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};*/

        int n = 10;
        int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

/*        int n = 1;
        int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};*/

        int[] answer = solution(n, info);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] answer = new int[11];
    public static boolean flag = false;
    public static int[] Info;
    public static int max = -987654321;

    public static int[] solution(int n, int[] info) {

        Info = info;
        int[] buckets = new int[11];
        getCase(n, buckets, buckets.length);

        if (!flag) {
            return new int[]{-1};
        }
        return answer;
    }

    public static void getCase(int n, int[] buckets, int k) {
        if (k == 0) {
            int total = 0;
            for (int i = 0; i < buckets.length; i++) {
                total += buckets[i];
            }

            if (total == n) {
                int apeachScore = 0;
                int lionScore = 0;
                for (int i = 0; i < buckets.length; i++) {
                    if (Info[i] == 0 && buckets[i] == 0) {
                        continue;
                    }
                    if (Info[i] >= buckets[i]) {
                        apeachScore += (10 - i);
                    } else {
                        lionScore += (10 - i);
                    }
                }

                int diff = lionScore - apeachScore;
                if (diff > 0 && max < diff) {
                    max = diff;
                    for (int i = 0; i < buckets.length; i++) {
                        answer[i] = buckets[i];
                        flag = true;
                    }
                }
            }
            return;
        }

        int lastIndex = buckets.length - k - 1;
        int total = 0;
        for (int i = 0; i <= lastIndex; i++) {
            total += buckets[i];
        }

        for (int i = 0; i <= n - total; i++) {
            buckets[lastIndex + 1] = i;
            getCase(n, buckets, k - 1);
        }
    }
}
