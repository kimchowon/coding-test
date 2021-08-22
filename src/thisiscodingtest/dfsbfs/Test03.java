package thisiscodingtest.dfsbfs;

import java.io.*;
import java.util.*;

/**
 * 기출01. 특정거리의 도시 찾기
 * 제한시간: 30분 / 소요시간: 32분 51초
 */
public class Test03 {
    static class Edge implements Comparable<Edge> {
        int destination; // 도착지
        int weight; // 가중치

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static List<Edge> edges[];
    public static boolean[] check;
    public static int[] dist;

    public static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도시 개수
        int M = Integer.parseInt(st.nextToken()); // 도로 개수
        K = Integer.parseInt(st.nextToken()); // 거리 정보
        int start = Integer.parseInt(st.nextToken()); // 출발 도시

        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        check = new boolean[N + 1];
        dist = new int[N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 출발지
            int e = Integer.parseInt(st.nextToken()); // 도착지

            Edge edge = new Edge(e, 1);
            edges[s].add(edge);
        }

        dikjstra(start);

        boolean flag = false;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == K) {
                flag = true;
                bw.write(i + "\n");
            }
        }

        if (!flag) {
            bw.write(-1 + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void dikjstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int destination = edge.destination;

            if (check[destination]) {
                continue;
            } else {
                check[destination] = true;
            }

            for (Edge next : edges[destination]) {
                if (dist[next.destination] >= dist[destination] + next.weight) {
                    dist[next.destination] = dist[destination] + next.weight;
                    pq.add(new Edge(next.destination, dist[next.destination]));
                }
            }
        }
    }

}
