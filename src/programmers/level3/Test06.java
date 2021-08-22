package programmers.level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test06 {
    public static void main(String[] args) {
        int n = 1;
        int times[] = {1, 9, 19, 30};

        System.out.println(solution(n, times));
    }

    static class Examiner implements Comparable<Examiner> {
        int index;
        int time;

        public Examiner(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Examiner o) {
            return this.time - o.time;
        }

        @Override
        public String toString() {
            return "Examiner{" +
                    "index=" + index +
                    ", time=" + time +
                    '}';
        }
    }

    public static long solution(int n, int[] times) {
        int answer = 0;

        List<Examiner> examinerList = new ArrayList<>();

        for (int i = 0; i < times.length; i++) {
            Examiner examiner = new Examiner(i, times[i]);
            examinerList.add(examiner);
        }
        Collections.sort(examinerList);

        int cnt = 0;
        while (cnt < n) {
            int min = examinerList.get(0).time;

            answer += min;

            for (int i = 0; i < examinerList.size(); i++) {
                int num = examinerList.get(i).time - min;
                if (num == 0) {
                    cnt++;
                    examinerList.get(i).time = times[examinerList.get(i).index];
                } else {
                    examinerList.get(i).time = num;
                }
            }
            Collections.sort(examinerList);
        }

        return answer;

    }
}
