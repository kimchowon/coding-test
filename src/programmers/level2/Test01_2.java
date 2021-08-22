package programmers.level2;

/**
 * 124 나라의 숫자
 */
public class Test01_2 {
    public static void main(String[] args) {
        int n = 10;
        System.out.println("answer :" + solution(n));
    }

    public static String solution(int n) {
        String answer = "";
        String[] remainderArray = {"4", "1", "2"};
        String remainder;

        while (n > 0) {
            remainder = remainderArray[n % 3];
            n = n / 3;

            if (remainder.equals("4")) {
                n = n - 1;
            }
            answer = remainder + answer;
        }
        return answer;
    }
}