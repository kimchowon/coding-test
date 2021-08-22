package thisiscodingtest.greedy;

import java.io.*;
import java.util.*;

public class Test03 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int count = solution(N, M);
        bw.write(count + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static int solution(int N, int M) {
        int count = 0;
        while (N > 1) {
            if (N % M != 0) {
                N = N -1;
                count++;
            }else {
                N = N / M;
                count++;
            }
        }
        return count;
    }
}
