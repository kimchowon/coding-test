package greedy;

import java.util.Arrays;

public class Solution4 {

    public static void main(String[] args) {

        int people[] = {10, 10, 10, 10, 70};
        System.out.printf("answer : " + solution(people, 100));

    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        // 사람들의 구명보트 탑승 여부 : 미탑승(false), 탑승(true)
        boolean[] embark = new boolean[people.length];

        // 사람들을 차례대로 순회
        for (int i = 0; i < people.length; i++) {

            // 구명보트에 탑승여부 체크, 이미 탑승한 사람이라면 패스
            if (!embark[i]) {
                // 탑승을 하지 않은 사람이므로 먼저 탑승 시킴
                embark[i] = true;

                answer++;

                // 무게 제한 - 탑승한 사람의 무게
                int minus = limit - people[i];

                int min = minus;
                int minIndex = 0;

                // 탑승한 사람 다음 순서 사람들을 모두 체크
                for (int j = i + 1; j < people.length; j++) {

                    // 아직 미탑승이고 남은 무게보다 적거나 같은 사람일 경우
                    if (!embark[j] && (people[j] <= minus)) {
                        int n = minus - people[j];
                        if (n < min) {
                            min = n;

                            // 아직 미탑승이면서 남은 무게와 가장 차이가 적은 사람을 선택
                            minIndex = j;
                        }
                    }
                }

                // 남은 무게와 가장 차이가 적은 사람을 선택하여 구명보트에 탑승 시킴
                embark[minIndex] = true;
            }
        }
        return answer;


    }
}
