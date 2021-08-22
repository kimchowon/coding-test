package programmers.level2;

public class Test29 {
    public static void main(String[] args) {
        int n = 8;
        int a = 4;
        int b = 7;

        System.out.println("answer : " + solution(n, a, b));
    }

    public static int solution(int n, int a, int b) {

        int min = Math.min(a, b);
        int max = Math.max(a, b);
        int round = 1;
        while ((min % 2 == 0) || (max % 2 == 1) || (max - min != 1)) {
            if (min % 2 == 0) {
                min = min / 2;
            } else {
                min = min / 2 + 1;
            }

            if (max % 2 == 0) {
                max = max / 2;
            } else {
                max = max / 2 + 1;
            }
            round++;
        }

        return round;
    }
}
