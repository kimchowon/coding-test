package baekjoon.gold1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Test06 {
    static int[] fail;
    static int answer = 0;
    static List<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        String w = br.readLine();

        // w에 대한 실패함수 설정
        fail = new int[w.length()];
        initFail(w);

        int sLen = s.length();
        int wLen = w.length();
        int j = 0; // w의 index
        for (int i = 0; i < sLen; i++) { // s의 인덱스
            while (j > 0 && s.charAt(i) != w.charAt(j)) {
                j = fail[j - 1];
            }
            if (s.charAt(i) == w.charAt(j)) {
                if (j == wLen - 1) {
                    answer++;
                    answerList.add(i - j + 1);
                    j = fail[j];
                } else {
                    j++;
                }
            }
        }

        bw.write(answer + "\n");
        for (int num : answerList) {
            bw.write(num + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    // 실패 함수
    public static void initFail(String w) {
        int i = 0;
        for (int j = 1; j < w.length(); j++) {
            while (i > 0 && w.charAt(i) != w.charAt(j)) {
                i = fail[i - 1];
            }

            if (w.charAt(i) == w.charAt(j)) {
                fail[j] = i + 1;
                i++;
            }
        }
    }
}
