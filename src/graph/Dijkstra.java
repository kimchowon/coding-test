package graph;

/**
 * 최단 경로 알고리즘: 다익스트라 알고리즘
 */
public class Dijkstra {
    private int n; // 노드들의 수
    private int maps[][]; // 노드들간의 가중치 저장할 변수

    Dijkstra(int n) {
        this.n = n;
        maps = new int[n + 1][n + 1];
    }

    public void input(int i, int j, int w) {
        maps[i][j] = w;
        // 무향그래프인 경우 maps[j][i] = w; 도 해줌.
    }


    public void dijkstra(int v) {
        int distance[] = new int[n + 1]; // 최단 거리를 저장할 변수
        boolean[] check = new boolean[n + 1]; // 해당 노드를 방문했는지 체크할 변수

        // distance값 초기화
        for (int i = 1; i < n + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        // 시작노드값 초기화
        distance[v] = 0;
        check[v] = true;

        // 연결노드 distance 갱신
        for (int i = 1; i < n + 1; i++) {
            if (!check[i] && maps[v][i] != 0) {
                distance[i] = maps[v][i];
            }
        }

        for (int a = 0; a < n - 1; a++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            // 최소값 찾기
            for (int i = 1; i < n + 1; i++) {
                if (!check[i] && distance[i] != Integer.MAX_VALUE) {
                    if (distance[i] < min) {
                        min = distance[i];
                        minIndex = i;
                    }
                }
            }

            check[minIndex] = true;
            for (int i = 1; i < n + 1; i++) {
                if (!check[i] && maps[minIndex][i] != 0) {
                    if (distance[i] > distance[minIndex] + maps[minIndex][i]) {
                        distance[i] = distance[minIndex] + maps[minIndex][i];
                    }
                }
            }
        }

        //결과값 출력
        for (int i = 1; i < n + 1; i++) {
            System.out.print(distance[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra(8);
        dijkstra.input(1, 2, 8);
        dijkstra.input(1, 3, 9);
        dijkstra.input(1, 4, 11);
        dijkstra.input(2, 5, 10);
        dijkstra.input(3, 2, 6);
        dijkstra.input(3, 4, 3);
        dijkstra.input(3, 5, 1);
        dijkstra.input(4, 7, 8);
        dijkstra.input(4, 6, 8);
        dijkstra.input(5, 8, 2);
        dijkstra.input(6, 3, 12);
        dijkstra.input(6, 8, 5);
        dijkstra.input(7, 6, 7);
        dijkstra.input(8, 7, 4);

        dijkstra.dijkstra(1);
    }
}
