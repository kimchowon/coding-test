package programmers.level2;

public class Test21 {

    public static void main(String[] args) {

        String s = "for the last week";
        System.out.println(solution(s));

    }

    public static String solution(String s) {
        String answer = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 문자열의 가장 첫번째 문자가 알파벳인 경우 - 대문자로 치환
            if (i == 0) {
                answer += String.valueOf(c).toUpperCase();
                continue;
            }else {
                // 현재 문자의 이전 문자가 공백인 경우 - 대문자로 치환
                if (s.charAt(i - 1) == ' ') {
                    answer += String.valueOf(c).toUpperCase();
                    continue;
                }
            }

            // 그 외 나머지 문자 - 소문자로 치환
            answer += String.valueOf(c).toLowerCase();
        }

        return answer;
    }
}
