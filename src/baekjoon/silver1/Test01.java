package baekjoon.silver1;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 1629 - 곱셈
 */
public class Test01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c= Integer.parseInt(st.nextToken());

        bw.write(pow(a, b, c) + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    static long pow(long a, long b, long c) {
        if(b == 1) {
            return a % c;
        }

        long temp = pow(a, b/2, c);

        // 지수가 홀수라면
        if(b % 2 == 1) {
            return (((temp * temp) % c) * a) % c;
        }
        return (temp * temp) % c;
    }
}
