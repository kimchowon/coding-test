package baekjoon.gold1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Test08 {
    public static long div = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long A = getFactorial(N);
        long B = getFactorial(M) * getFactorial(N - M) % div;

        long answer = ((A % div) * (getPow(B, div - 2))) % div;

        bw.write(answer + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    public static long getFactorial(long num) {
        long answer = 1L;
        while (num > 1) {
            answer*=(num % div);
            num--;
        }
        return answer;
    }

    public static long getPow(long a, long b) {
        if(b == 1) {
            return a % div;
        }

        long temp = getPow(a, b/2);

        // 지수가 홀수라면
        if(b % 2 == 1) {
            return (((temp * temp) % div) * a) % div;
        }
        return (temp * temp) % div;
    }
}
