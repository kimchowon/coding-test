package baekjoon.gold5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1753 - 최단경로
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
            return this.weight - o.weight;
        }
    }

    public static ArrayList<Edge> adj[];
    public static boolean[] check;
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        adj = new ArrayList[V+1];
        for (int i=1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i=1; i <=E; i++) {
            st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());

            adj[num1].add(new Edge(num2, num3));
        }

        check = new boolean[V+1];
        dist = new int[V+1];
        dijkstra(start);

        for (int i=1; i < dist.length; i++) {
            if (dist[i]==Integer.MAX_VALUE) {
                bw.write("INF" + "\n");
            }else {
                bw.write(dist[i]+"\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int destination = edge.destination;

            if (check[destination]) {
                continue;
            }else {
                check[destination] = true;

                for (Edge next : adj[destination]) {
                    if (dist[next.destination] >= dist[destination] + next.weight) {
                        dist[next.destination] = dist[destination] + next.weight;
                        pq.add(new Edge(next.destination, dist[next.destination]));
                    }
                }
            }
        }
    }
}
