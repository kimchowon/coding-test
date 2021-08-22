package programmers.level2;

public class Test26 {
    public static void main(String[] args) {
        int n = 15;

        System.out.println("answer : " + solution(n));

    }

    public static int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (isExpress(i, n)) {
                answer++;
            }
        }
        return answer;
    }

    public static boolean isExpress(int first, int n) {
        int sum = 0;
        for (int i = first; i <= n; i++) {
            sum += i;

            if (sum > n) {
                return false;
            }
            if (sum == n) {
                return true;
            }
        }
        return false;
    }


}
