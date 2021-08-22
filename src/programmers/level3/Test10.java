package programmers.level3;

public class Test10 {

    public static long answer = 0;

    public static void main(String[] args) {
        System.out.println("solution=" + solution(1));
    }

    public static long solution(int n) {
        long answer = 0;
        int divide = 1234567;

        int[] fib = new int[n+2];
        fib[1] = 1;
        fib[2] = 2;

        for (int i=3; i <=n; i++) {
            fib[i] = ((fib[i-1]%divide) + (fib[i-2]%divide))%divide;
        }
        return fib[n];
    }
}
