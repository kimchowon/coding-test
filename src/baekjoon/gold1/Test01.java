package baekjoon.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        long min = Long.parseLong(strs[0]);
        long max = Long.parseLong(strs[1]);
        solution(min, max);
    }

    public static void solution(long min, long max) {
        boolean[] checkArray = new boolean[1000001];
        Arrays.fill(checkArray, false);

        int end = (int) Math.sqrt(max);
        for (int i = 2; i <= end; i++) {
            long pow = (long) Math.pow(i, 2);
            long count = min / pow == 0 ? pow : (min / pow) * pow;
            for (long j = count; j <= max; j += pow) {
                if (j >= min) {
                    if (checkArray[(int) (j - min)]) {
                        continue;
                    }
                    checkArray[(int) (j - min)] = true;
                }
            }
        }

        long answer = 0;
        for (int i = 0; i < checkArray.length; i++) {
            answer += checkArray[i] == true ? 1 : 0;
        }

        System.out.println(max - min + 1 - answer);
    }
}
