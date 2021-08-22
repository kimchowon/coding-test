package heap;

import java.util.PriorityQueue;

public class Test1 {
    public static void main(String[] args) {

        int[] scovilee = {1, 1};
        int K = 7;

        System.out.println("answer : " + solution(scovilee, K));
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i : scoville) { pq.add(i); }

        while (!pq.isEmpty() && pq.peek() < K) {
            if(pq.size()==1 && pq.peek() < K) { return -1; }

            int n1 = 0, n2 = 0;
            if(!pq.isEmpty()) { n1 = pq.poll(); }
            if(!pq.isEmpty()) { n2 = pq.poll(); }

            int k = n1 + (n2 * 2);
            pq.add(k);
            answer++;
        }
        return answer;
    }


}
