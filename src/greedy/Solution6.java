package greedy;

import java.util.PriorityQueue;

public class Solution6 {
    static class A implements Comparable<A> {
        int start;
        int end;

        A(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(A o) {
            return o.start >= this.start ? 1 : -1;
        }
    }

    static PriorityQueue<A> pq;

    public static void main(String[] args) {

       /* print(solution({{-2,-1}, {1,2},{-3,0}})) #2
        print(solution({{0,0},})) #1
        print(solution({{0,1}, {0,1}, {1,2}})) #1
        print(solution({{0,1}, {2,3}, {4,5}, {6,7}})) #4
        print(solution({{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}})) #2
        print(solution({{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}})) #2
        print(solution({{-20,15}, {-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}})) #2*/

      //  int[][] routes = {{0,1}, {0,1}, {1,2}};
        int[][] routes ={{0,0},};
        System.out.println("answer : " + solution(routes));
    }

    public static int solution(int[][] routes) {
        int answer = 0;
        pq = new PriorityQueue<>();

        for(int i=0; i < routes.length; i++) {
            pq.add(new A(routes[i][0], routes[i][1]));
        }

        A a = pq.poll();
        answer++;
        while (!pq.isEmpty()) {
            if(!pq.isEmpty()) {
                A b = pq.peek();

                if(a.start <= b.end) {
                    pq.poll();
                }else {
                    a = pq.poll();
                    answer++;
                }
            }
        }
        return answer;
    }


}
