package programmers.level3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Test04 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        System.out.println(solution(n, edge));

    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;

        LinkedList<Integer>[] linkedLists = new LinkedList[n + 1];
        for (int i = 1; i < linkedLists.length; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            linkedLists[edge[i][0]].add(edge[i][1]);
            linkedLists[edge[i][1]].add(edge[i][0]);
        }

        boolean[] visited = new boolean[n + 1];
        int[] countList = getEdgeCount(visited, linkedLists);

        Arrays.sort(countList);
        int max = countList[countList.length-1];
        for (int i=countList.length-1; i >=0; i--) {
            if (max == countList[i]) {
                answer++;
            }else {
                return answer;
            }
        }


        return answer;
    }

    public static int[] getEdgeCount(boolean[] visited, LinkedList[] linkedLists) {
        int[] countList = new int[linkedLists.length];
        Queue<Integer> queue = new LinkedList<>();

        visited[1] = true;
        for (int i = 0; i < linkedLists[1].size(); i++) {
            int num = (int) linkedLists[1].get(i);
            queue.add(num);
            countList[num] = 1;
            visited[num] = true;
        }

        while (!queue.isEmpty()) {
            int num = queue.poll();

            for (int i = 0; i < linkedLists[num].size(); i++) {
                int node = (int) linkedLists[num].get(i);

                if (!visited[node]) {
                    queue.add(node);
                    countList[node] = countList[num] + 1;
                    visited[node] = true;
                }
            }
        }

        return countList;
    }
}

