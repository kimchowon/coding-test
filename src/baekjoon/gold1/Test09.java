package baekjoon.gold1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 2887 - 행성 터널
 */
public class Test09 {
    // 행성
    static class Planet {
        int order;
        int x;
        int y;
        int z;

        public Planet(int order, int x, int y, int z) {
            this.order = order;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Node implements Comparable<Node> {
        int start;
        int end;
        int w; // 시작 행성과 끝 행성 사이 간선의 가중치

        public Node(int start, int end, int w) {
            this.start = start;
            this.end = end;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w >= o.w ? 1 : -1;
        }
    }

    public static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        parent = new int[N];

        List<Planet> planetList = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planetList.add(new Planet(i, x, y, z));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        Collections.sort(planetList, (o1, o2) -> o1.x >= o2.x ? 1 : -1);
        for (int i = 0; i < N - 1; i++) {
            pq.add(new Node(planetList.get(i).order, planetList.get(i + 1).order,
                    Math.abs(planetList.get(i).x - planetList.get(i + 1).x)));
        }

        Collections.sort(planetList, (o1, o2) -> o1.y >= o2.y ? 1 : -1);
        for (int i = 0; i < N - 1; i++) {
            pq.add(new Node(planetList.get(i).order, planetList.get(i + 1).order,
                    Math.abs(planetList.get(i).y - planetList.get(i + 1).y)));
        }

        Collections.sort(planetList, (o1, o2) -> o1.z >= o2.z ? 1 : -1);
        for (int i = 0; i < N - 1; i++) {
            pq.add(new Node(planetList.get(i).order, planetList.get(i + 1).order,
                    Math.abs(planetList.get(i).z - planetList.get(i + 1).z)));
        }

        int answer = 0;
        int E = pq.size();
        for (int i = 0; i < E; i++) {
            Node node = pq.poll();

            int ps = findSet(node.start);
            int pe = findSet(node.end);

            if (ps == pe) continue;

            unionSet(node.start, node.end);
            answer += node.w;
        }

        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();
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
