package thisiscodingtest.implement;

import java.io.IOException;

/**
 * 09. 문자열 압축
 * 제한시간: 30분 / 소요시간: 29분 30초
 *
 */
public class Test07 {
    public static void main(String[] args) throws IOException {
     //   String s = "aabbaccc";
     //   String s = "ababcdcdababcdcd";
      //  String s = "abcabcdede";
      //  String s = "abcabcabcabcdededededede";
        String s = "xababcdcdababcdcd";
      //  String s = "a";
        int answer = solution(s);
        System.out.println(answer);
    }

    public static int solution(String s) {
        int answer = Integer.MAX_VALUE;

        if (s.length()==1) {
            return 1;
        }

        int len = s.length() / 2;
        for (int i = 1; i <= len; i++) {
            int sLen = compressString(i, s);
            answer = Math.min(answer, sLen);
        }
        return answer;
    }

    public static int compressString(int num, String s) {
        String answerS = "";
        String subS = s.substring(0, num);
        int count = 1;

        int i;
        for (i=num; i < s.length(); i+=num) {
            if (i + num <= s.length()) {
                String tempS = s.substring(i, i + num);
                if (subS.equals(tempS)) {
                    count++;
                }else {
                    answerS += ((count==1?"":count) + subS);
                    subS = tempS;
                    count = 1;
                }
            }else {
                break;
            }
        }
        answerS += ((count==1?"":count) + subS);
        answerS += s.substring(i);
        return answerS.length();
    }
}
