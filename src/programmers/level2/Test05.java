package programmers.level2;

import java.util.Arrays;

public class Test05 {

    public static void main(String[] args) {
        int citations[] = {3, 0, 6, 1, 5};
        System.out.println("answer : " + solution(citations));
    }

    public static int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        int h = 0, i = 0;
        int length = citations.length;
        while (h <= length - i) {

            for (i = 0; i < length; i++) {
                if (h <= citations[i]) {
                    if (h <= (length - i)) {
                        answer = answer < h ? h : answer;
                    }
                }
            }
            h++;
            i = 0;
        }
        return answer;
    }
}
