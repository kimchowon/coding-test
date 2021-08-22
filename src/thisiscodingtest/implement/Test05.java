package thisiscodingtest.implement;

import java.io.*;

/**
 * 07. 럭키 스트레이트
 * 제한시간: 20분 / 소요시간: 6분 43초
 */
public class Test05 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String N = br.readLine();
        String num1 = N.substring(0, N.length() / 2);
        String num2 = N.substring(N.length() / 2);

        int sum1 = 0;
        for (int i = 0; i < num1.length(); i++) {
            sum1 += Integer.parseInt(num1.charAt(i) + "");
        }

        int sum2 = 0;
        for (int i = 0; i < num2.length(); i++) {
            sum2 += Integer.parseInt(num2.charAt(i) + "");
        }

        if (sum1 == sum2) {
            bw.write("LUCKY");
        } else {
            bw.write("READY");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
