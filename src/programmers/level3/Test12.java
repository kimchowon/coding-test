package programmers.level3;

import java.util.*;

/**
 * 프로그래머스. Level3. 가장 긴 팰린드롬
 *
 * 정확도는 맞지만 효율성 테스트에서 실패하는 풀이
 */
public class Test12 {
    public static void main(String[] args) {
        String s = //"abcdcba";
        "fgldppoweiflgjsomemeninterpretninememos";
        System.out.println("길이=" + s.length());
        System.out.println(solution(s));

    }

    public static int solution(String s) {
        int answer = 1;

        int sLength = s.length();
        for (int i = 0; i < sLength; i++) {
            int start = 0;
            int end = 2 * i + 1;
            int count = 0;
            boolean check = false;
            while (count < 2 * i + 1) {
                if (check) { break; }

                String sub_s;
                if (start >= 0 && end < sLength) {
                    if (start <= i && end >= i + 1) {
                        int length = end - start + 1;
                        if (length > answer) {
                            sub_s = s.substring(start, end + 1);
                            System.out.println(sub_s);

                            if (isPalindrome(sub_s)) {
                                System.out.println(sub_s+"-->펠린드롬");
                                check = true;
                                answer = Math.max(answer, sub_s.length());
                            }
                        }else {
                            break;
                        }

                    }
                }
                end--;

                if (check) { break; }

                if (start >= 0 && end < sLength) {
                    if (start <= i && end >= i + 1) {
                        int length = end - start + 1;
                        if (length > answer) {
                            sub_s = s.substring(start, end + 1);
                            System.out.println(sub_s);

                            if (isPalindrome(sub_s)) {
                                System.out.println(sub_s+"-->펠린드롬");
                                check = true;
                                answer = Math.max(answer, sub_s.length());
                            }
                        }
                        else {
                            break;
                        }
                    }
                }
                start++;
                count++;
            }
        }
        return answer;
    }

    public static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
