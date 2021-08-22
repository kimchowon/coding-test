package programmers.level3;

import java.util.LinkedList;

public class Test09 {
    public static void main(String[] args) {

    }

    static LinkedList<Integer>[] adj;
    static class Order {

        Order(int size) {
            adj = new LinkedList[size];

            for (int i=0; i < adj.length; i++) {
                adj[i] = new LinkedList<>();
            }
        }

    }

    public int solution(int n, int[][] results) {
        int answer = 0;
        return answer;
    }
}
