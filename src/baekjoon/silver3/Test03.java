package baekjoon.silver3;

import java.io.*;
import java.util.StringTokenizer;

public class Test03 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] buckets = new int[M];
        combination(N, buckets, buckets.length);

        br.close();
        bw.flush();
        bw.close();
    }

    public static void combination(int N, int[] buckets, int k) {
        if (k == 0) {
            for (int item : buckets) {
                System.out.print(item + " ");
            }
            System.out.println();
            return;
        }

        int lastIndex = buckets.length - k - 1;

        for (int i=1; i <= N; i++) {
            boolean flag = true;

            for (int j=0; j <= lastIndex; j++) {
                if (buckets[j] == i) {
                    flag = false;
                }
            }

            if (flag) {
                buckets[lastIndex+1] = i;
                combination(N, buckets, k-1);
            }
        }
    }
}
