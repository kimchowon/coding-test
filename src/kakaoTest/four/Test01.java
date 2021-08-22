package kakaoTest.four;

/**
 * n진수 게임 Lv.2
 * 소요시간: 45분 3초
 */
public class Test01 {
    public static void main(String[] args) {
        int n = 2; // 진법 n
        int t = 4; // 구할 숫자의 갯수 t
        int m = 2; // 게임에 참가하는 인원 m
        int p = 1; // 튜브의 순서 p

        String answer = solution(n, t, m, p);
        System.out.println(answer);
    }

    public static String solution(int n, int t, int m, int p) {
        String answer = "";

        int num = 0;
        int totalCount = ((t - 1) * m) + p; // 돌아야 하는 총 순서의 수
        String totalOutput = " ";
        int count = 0;
        while (count < totalCount) {
            String N = getConventN(num, n); // 숫자를 N진수로 변환
            for (int i = 0; i < N.length(); i++) {
                totalOutput += N.charAt(i);
                count++;
            }
            num++;
        }

        for (int i = p; i < totalOutput.length(); i += m) {
            answer += totalOutput.charAt(i);
        }

        return answer.substring(0, t);
    }

    // 10진수를 N진수로 변환
    public static String getConventN(int num, int n) {
        String result = "";
        if (num == 0) {
            return "0";
        }

        while (num > 0) {
            int share = num / n;
            int remainder = num % n;
            if (remainder > 9) {
                result = (char) (remainder + 55) + result;
            } else {
                result = remainder + result;
            }
            num = share;
        }
        return result;
    }
}
