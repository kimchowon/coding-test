package programmers.level2;

public class Test19 {
    public static void main(String[] args) {
        int n = 10000;
        System.out.println("answer : " + solution(n));
    }

    public static int solution(int n) {
        int  answer = 0;
        int [] memo = new int [n+1];
        memo[0] = 0;
        memo[1] = 1;

        int param = 1234567;

        answer = fib(n, param, memo) % param;
        return answer;
    }

    public static int  fib(int n, int param, int [] memo) {
        if (n==0) {
            return memo[0];
        }

        if (n==1) {
            return memo[1];
        }

        if (memo[n]!=0) {
            return memo[n];
        }

        memo[n] = ((fib(n-1, param, memo) % param) + (fib(n-2, param, memo) % param));
        return memo[n];
    }
}
