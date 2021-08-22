package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    static int v;
    static LinkedList<Integer>[] adj;

    BFS(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    static void bfs(int s) {
        boolean[] visited = new boolean[adj.length];
        Queue<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.println("s : " + s);

            Iterator<Integer> iterator = adj[s].listIterator();
            while (iterator.hasNext()) {
                int num = iterator.next();

                if (!visited[num]) {
                    visited[num] = true;
                    queue.add(num);
                }
            }
        }
    }

    public static void main(String[] args) {
        BFS bfs = new BFS(9);

        bfs.addEdge(1, 2);
        bfs.addEdge(1, 3);
        bfs.addEdge(1, 4);
        bfs.addEdge(2, 1);
        bfs.addEdge(2, 3);
        bfs.addEdge(3, 1);
        bfs.addEdge(3, 2);
        bfs.addEdge(3, 4);
        bfs.addEdge(3, 5);
        bfs.addEdge(4, 1);
        bfs.addEdge(4, 3);
        bfs.addEdge(4, 6);
        bfs.addEdge(4, 7);
        bfs.addEdge(5, 3);
        bfs.addEdge(6, 4);
        bfs.addEdge(6, 7);
        bfs.addEdge(6, 8);
        bfs.addEdge(7, 4);
        bfs.addEdge(7, 6);
        bfs.addEdge(7, 8);
        bfs.addEdge(8, 6);
        bfs.addEdge(8, 7);


        bfs(1);


     /*   BFS b = new BFS(4);

        b.addEdge(0, 1);
        b.addEdge(0, 2);
        b.addEdge(1, 2);
        b.addEdge(2, 0);
        b.addEdge(2, 3);
        b.addEdge(3, 3);

        b.bfs(2);*/
    }
}
