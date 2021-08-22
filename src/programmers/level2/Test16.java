package programmers.level2;

public class Test16 {
    public static void main(String[] args) {
        int n = 78;
        System.out.println(solution(n));
    }

    public static int solution(int n) {
        // 숫자 n을 이진수로 변환했을 때 1의 개수
        int one_count = Integer.bitCount(n);

        int answer_count = 0;
        while (one_count != answer_count) {
            // n보다 큰 숫자들을 하나씩 순회
            n++;
            answer_count = Integer.bitCount(n);
        }
        return n;
    }

}
