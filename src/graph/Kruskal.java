package graph;

import java.util.PriorityQueue;

/**
 * 크루스칼 알고리즘
 */
public class Kruskal {
    public static int[] parent;

    // 각 집합들의 연결 관계
    // 양쪽 노드(시작 노드, 끝 노드) 정의
    // 노드 사이의 가중치 정의
    static class Node implements Comparable<Node> {
        int s; // start(시작 노드)
        int e; // end(끝 노드)
        int w; // weight(가중치)


        public Node(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w >= o.w ? 1 : -1;
        }
    }

    public static void main(String[] args) {
        int result = 0; // 최소 신장트리 가중치의 합
        int N = 7; // 노드 개수
        int E = 10; // 간선 개수

        // 1.
        // 초기화
        // 각각 본인을 대표값(루트노드)로 하는 집합 생성
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            makeSet(i);
        }

        // 2.
        // 집합들을 이어 그래프 생성
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위큐로 그래프 생성. 가중치가 가장 작은 간선을 계속 찾아야 하므로 가중치 크기 순으로 나열되게끔
        pq.add(new Node(0, 1, 8));
        pq.add(new Node(0, 2, 9));
        pq.add(new Node(0, 3, 11));
        pq.add(new Node(1, 4, 14));
        pq.add(new Node(2, 3, 13));
        pq.add(new Node(2, 4, 5));
        pq.add(new Node(2, 5, 12));
        pq.add(new Node(3, 5, 8));
        pq.add(new Node(3, 6, 8));
        pq.add(new Node(5, 6, 7));

        // 3.
        // 간선들을 하나씩 순회하며 최소 가중치인 간선부터 탐색
        for (int i = 0; i < E; i++) {
            Node node = pq.poll();

            int s = node.s;
            int e = node.e;

            int pS = findSet(s);
            int pE = findSet(e);

            if (pS == pE) continue;

            unionSet(s, e);
            result += node.w;
        }

        System.out.println("result : " + result);
    }

    public static void makeSet(int x) {
        parent[x] = x;
    }

    public static int findSet(int x) {
        if (x == parent[x]) {
            return x;
        }

        parent[x] = findSet(parent[x]);
        return parent[x];
    }

    public static void unionSet(int x, int y) {
        int pX = findSet(x);
        int pY = findSet(y);

        if (pX != pY) {
            parent[pY] = pX;
        }
    }
}
