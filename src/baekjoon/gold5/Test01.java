package baekjoon.gold5;

import java.io.*;

/**
 * https://hoho325.tistory.com/91
 */
public class Test01 {
    public static int N;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        int[] buckets = new int[N];
        nQueen(buckets, 0);

        bw.write(count + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void nQueen(int[] buckets, int k) {
        if (k == N) {
            count++;
            return;
        }

        int lastIndex = k - 1;
        for (int i = 0; i < N; i++) {
            boolean flag = true;

            for (int j = 0; j <= lastIndex; j++) {
                if (buckets[j] == i) {
                    flag = false;
                }

                if (Math.abs(j - k) == Math.abs(buckets[j] - i)) {
                    flag = false;
                }
            }

            if (flag) {
                buckets[lastIndex + 1] = i;
                nQueen(buckets, k + 1);
            }
        }
    }
}
