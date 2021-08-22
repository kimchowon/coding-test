package greedy;

import java.util.PriorityQueue;

/**
 * kruskal 알고리즘
 */

public class Kruskal {
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

    static int[] parent;

    public static void main(String[] args) {
        int N = 7;
        int E = 5;

        parent = new int[N+1];
        int result = 0;

        for(int i=0; i <= N; i++) {
            parent[i] = i;
        }


        PriorityQueue<A> pq = new PriorityQueue<>(); // 넣을때마다 최소 순으로 정렬이 됨
        pq.add(new A(0,1,1));
        pq.add(new A(0,2,2));
        pq.add(new A(1,2,5));
        pq.add(new A(1,3,1));
        pq.add(new A(2,3,8));
        /*pq.add(new A(4,1,10));
        pq.add(new A(1,3,3));
        pq.add(new A(2,3,3));
        pq.add(new A(3,7,4));
        pq.add(new A(3,6,3));
        pq.add(new A(3,5,6));*/


        for(int i=0; i < E; i++) {
            A node = pq.poll();

            int start = node.s;
            int end = node.e;

            int a = find(start);
            int b = find(end);

            if(a == b) continue;

            union(start, end);
            result+=node.v;

        }
        System.out.println("최소 비용 : " + result);
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
