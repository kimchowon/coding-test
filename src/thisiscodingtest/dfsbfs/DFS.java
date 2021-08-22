package thisiscodingtest.dfsbfs;

import java.util.LinkedList;

public class DFS {
    public static LinkedList<Integer>[] adj;
    public static boolean[] visited;

    public static void main(String[] args) {
        int N = 9;

        adj = new LinkedList[N];
        for (int i = 1; i < N; i++) {
            adj[i] = new LinkedList<>();
        }
        visited = new boolean[N];

        adj[1].add(2);
        adj[1].add(3);
        adj[1].add(8);
        adj[2].add(7);
        adj[3].add(4);
        adj[3].add(5);
        adj[4].add(5);
        adj[7].add(6);
        adj[7].add(8);

        dfs(1);
    }

    public static void dfs(int node) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int i = 0; i < adj[node].size(); i++) {
            int nextNode = adj[node].get(i);

            if (!visited[nextNode]) {
                dfs(nextNode);
            }
        }
    }
}