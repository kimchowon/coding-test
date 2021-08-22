package greedy;

import java.util.PriorityQueue;

/**
 * 프로그래머스 > 탐욕법 > 섬 연결하기
 */
public class Solution5 {
    static class A implements Comparable<A> {

        int s;
        int e;
        int v;

        A(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(A o) {
            return o.v >= this.v ? -1 : 1;
        }
    }

    static PriorityQueue<A> pq;
    static int[] parent;

    public static void main(String[] args) {
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        System.out.println(solution(4, costs));
    }

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];

        for(int i=0; i < n; i++) {
            parent[i] = i;
        }

        pq = new PriorityQueue<>();

        for(int i =0; i < costs.length; i++) {
            pq.add(new A(costs[i][0], costs[i][1], costs[i][2]));
        }

        for(int i=0; i < costs.length; i++) {
            A node = pq.poll();

            int start = node.s;
            int end = node.e;

            int a = find(start);
            int b = find(end);

            if(a == b) continue;

            union(start, end);
            answer+=node.v;

        }
        return answer;
    }

    public static int find(int a) {
        if(a == parent[a]) return a;
        parent[a] = find(parent[a]);

        return parent[a];
    }

    public static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot != bRoot) {
            parent[aRoot] = b;
        }else {
            return;
        }
    }
}
