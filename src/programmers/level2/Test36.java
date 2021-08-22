package programmers.level2;

import java.util.ArrayList;
import java.util.List;

public class Test36 {
    public static void main(String[] args) {

        int n = 16;
        int t = 16;
        int m = 2;
        int p = 2;

        System.out.println(solution(n, t, m, p));
    }

    public static String solution(int n, int t, int m, int p) {
        String answer = "";

        int count = 0; // 튜브가 말해야하는 숫자 개수 카운트

        // n진수로 변환할 숫자(0부터 1씩 증가)
        int num = 0;

        // 게임에 참가하는 사람들의 수만큼 리스트 생성
        List<Character> list = new ArrayList<>();

        while (count < t) {
            String convertN = getConventN(num, n, "");

            for (int i = 0; i < convertN.length(); i++) {
                char c = convertN.charAt(i);

                if (count == t) {
                    break;
                }

                if (list.size() < m) {
                    list.add(c);
                }

                if (list.size() == m) {
                    answer += list.get(p - 1);
                    count++;
                    list.clear();
                }
            }
            num++;
        }
        return answer;
    }

    public static String getConventN(int num, int n, String result) {
        if (num == 0) {
            if (result.equals("")) {
                return "0";
            }
            return result;
        }

        int remainder = num % n;
        if (remainder > 9) {
            result = (char) (remainder + 55) + result;
        } else {
            result = remainder + result;
        }
        return getConventN(num / n, n, result);
    }
}
