package heap;

import java.util.*;

public class Test2 {
    public static void main(String[] args) {

        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}, {50,7}};

        System.out.println("answer:" + solution(jobs));

    }

    public static class Job {
        int wait;
        int execute;
        int index;

        public Job(int wait, int execute, int index) {
            this.wait = wait;
            this.execute = execute;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "wait=" + wait +
                    ", execute=" + execute +
                    ", index=" + index +
                    '}';
        }
    }

    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.execute - o2.execute;
        }
    }

    public static int solution(int[][] jobs) {
        int answer = 0;

        PriorityQueue<Job> pq = new PriorityQueue<>(new JobComparator());
        for (int i = 0; i < jobs.length; i++) {
            Job job = new Job(jobs[i][0], jobs[i][1], i);
            pq.add(job);
        }

        int n = pq.peek().wait;
        int count=0;
        List<Integer> indexList = new ArrayList<>();
        while (!pq.isEmpty()) {
            Job job = pq.poll();
            System.out.println("----------------------------------");
            System.out.println("1: " + job.toString());

            if(indexList.contains(job.index)) { continue;}

            System.out.println("n=" + n);
            System.out.println("wait=" + job.wait);
            if(n >= job.wait) {
                n = n + job.execute;
                answer += (n - job.wait);

                System.out.println("2 n: " + n);
                System.out.println("2 answer:" + answer);
            }else {
                int[] one_job = jobs[count++];

                System.out.println("one job excuteTime : " + one_job[1]);
                indexList.add(count);

                n = n + one_job[1];
                answer += (n - one_job[0]);

            }
            count++;
        }
        return answer / jobs.length;
    }
}
