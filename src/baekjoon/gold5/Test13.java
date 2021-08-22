package baekjoon.gold5;

import java.io.*;
import java.util.*;

/**
 * 1916 - 최소비용 구하기
 */
public class Test13 {
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

    public static ArrayList<Edge> edges[];
    public static boolean[] check;
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 도시 개수 = 정점 개수
        int M = Integer.parseInt(br.readLine()); // 버스 개수

        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        check = new boolean[N + 1];
        dist = new int[N + 1];

        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 출발지
            int destination = Integer.parseInt(st.nextToken()); // 도착지
            int cost = Integer.parseInt(st.nextToken()); // 비용

            Edge edge = new Edge(destination, cost);
            edges[start].add(edge);
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st2.nextToken()); // 출발지
        int destination = Integer.parseInt(st2.nextToken()); // 도착지

        dikjstra(start);
        bw.write(dist[destination] + "\n");

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
