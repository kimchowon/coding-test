package baekjoon.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    static class Edge implements Comparable<Edge> {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        // 가중치가 낮은 순서대로(오름차순)
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static ArrayList<Edge> adj[];
    static boolean[] check;
    static int[] dist;

    public static void main(String[] args) {
        int V = 8;
        int E = 14;
        int start = 1;

        // A:1, B:2, C:3, D:4, E:5, F:6, G:7, H:8
        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        // 그래프 생성

        // A
        adj[1].add(new Edge(2, 8)); // A -8-> B
        adj[1].add(new Edge(3, 9)); // A -9-> C
        adj[1].add(new Edge(4, 11)); // A -11-> D

        // B
        adj[2].add(new Edge(5, 10)); // B -10-> E

        // C
        adj[3].add(new Edge(2, 6)); // C -6-> B
        adj[3].add(new Edge(5, 1)); // C -1-> E
        adj[3].add(new Edge(4, 3)); // C -3-> D

        // D
        adj[4].add(new Edge(6, 8)); // D -8-> F
        adj[4].add(new Edge(7, 8)); // D -8-> G

        // E
        adj[5].add(new Edge(8, 2)); // E -2-> H

        // F
        adj[6].add(new Edge(3, 12)); // F -12-> C
        adj[6].add(new Edge(8, 5)); // F -5-> H

        // G
        adj[7].add(new Edge(6, 7)); // G -7-> F

        // H
        adj[8].add(new Edge(7, 4)); // H -4-> G

        check = new boolean[V + 1];
        dist = new int[V + 1];

        dijkstra(start);

        String[] startStr = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 1; i < dist.length; i++) {
            System.out.println(startStr[i - 1] + "=" + dist[i]);
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

            if (check[destination])
                continue;
            else
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
