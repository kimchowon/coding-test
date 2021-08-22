package programmers.level2;

public class Test28 {
    public static void main(String[] args) {

        int n = 5000;
        System.out.println("answer : " + solution(n));

    }

    public static int solution(int n) {
        int ans = 0;

       while (n != 0) {
           if (n % 2 == 0) {
               n = n / 2;
           }else {
               n = n -1;
               ans++;
           }
       }
        return ans;
    }
}
