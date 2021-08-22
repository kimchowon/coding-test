package thisiscodingtest.dijkstra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    static class Edge implements Comparable<Edge> {
        int destination; // 도착지
        int weight; // 가중치

        Edge(int destination, int weight) {
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
        int V = 8; // 정점 개수
        int E = 14; // 간선 개수
        int start = 1; // 출발지

        edges = new ArrayList[V + 1];
        for (int i = 1; i <= 8; i++) {
            edges[i] = new ArrayList<>();
        }

        // 그래프 생성
        // A:1, B:2, C:3, D:4, E:5, F:6, G:7, H:8
        edges[1].add(new Edge(2, 8)); // A -8-> B
        edges[1].add(new Edge(3, 9)); // A -9-> C
        edges[1].add(new Edge(4, 11)); // A -11-> D

        edges[2].add(new Edge(5, 10));// B -10-> E

        edges[3].add(new Edge(2, 6));// C -6-> B
        edges[3].add(new Edge(5, 1));// C -1-> E
        edges[3].add(new Edge(4, 3));// C -3-> D

        edges[4].add(new Edge(6, 8));// D -8-> F
        edges[4].add(new Edge(7, 8));// D -8-> G

        edges[5].add(new Edge(8, 2));// E -2-> H

        edges[6].add(new Edge(3, 12));// F -12-> C
        edges[6].add(new Edge(8, 5));// F -5-> H

        edges[7].add(new Edge(6, 7));// G -7-> F

        edges[8].add(new Edge(7, 4)); // H -4-> G

        check = new boolean[V + 1];
        dist = new int[V + 1];

        dijkstra(start);

        String[] strings = {"", "A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 1; i <= 8; i++) {
            System.out.println(strings[i] + " = " + dist[i]);
        }
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
