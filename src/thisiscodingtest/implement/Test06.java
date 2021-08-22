package thisiscodingtest.implement;

import java.io.*;
import java.util.*;

/**
 * 문자열 재정렬
 * 제한시간: 20분 / 소요시간: 5분 51초
 */
public class Test06 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String N = br.readLine();
        List<String> alphabetList = new ArrayList<>();
        int sum = 0;
        for (int i=0; i < N.length(); i++) {
            char c = N.charAt(i);

            // 문자가 알파벳 대문자이면
            if (65 <= c && 90 >= c) {
                alphabetList.add(c+"");
            }else { // 숫자면
                sum += Integer.parseInt(c+"");
            }
        }
        Collections.sort(alphabetList);
        String result = "";
        for (String a : alphabetList) {
            result += a;
        }
        result += sum;
        bw.write(result);
        br.close();
        bw.flush();
        bw.close();
    }
}
