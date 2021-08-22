package programmers.level3;

public class Test02 {
    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    public static int solution(int n) {
        int answer = 0;

        int[] nArray = new int[n + 1];
        nArray[1] = 1;
        nArray[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            nArray[i] = (nArray[i - 2] + nArray[i - 1]) % 1000000007;
        }
        answer = nArray[n];
        return answer;
    }
}
