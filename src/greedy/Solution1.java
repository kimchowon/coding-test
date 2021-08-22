package greedy;

import java.util.stream.IntStream;

public class Solution1 {

    public static void main(String[] args) {
        int n = 5;
        int lost[] = {2,4};
        int reserve[] = {1,3,5};

        System.out.println("answer = " + solution(n, lost, reserve));
    }

    public static int solution(int n, int[] lost, int[] reserve) {

        int answer= 0;
        boolean[] visited = new boolean[n+2];

        boolean isLost = false;
        boolean isResreve = false;
        for(int i =1; i < n+1; i++) {
            int finalI = i;

            isLost = IntStream.of(lost).anyMatch(x -> x == finalI);
            isResreve = IntStream.of(reserve).anyMatch(x -> x == finalI);

            if(!isLost && !isResreve) {
                visited[finalI] = true;
            }

            if(isResreve) {
                visited[finalI] = true;
                greedy(visited, lost, finalI);
            }

        }

        for(int i=0; i < visited.length; i++) {
            if (visited[i]) {
                answer++;
            }
        }

        return answer;
    }

    public static void greedy(boolean[] visited, int[] lost, int reserve) {

        if (IntStream.of(lost).anyMatch(x -> x == reserve)) {
            return;
        }

        if (IntStream.of(lost).anyMatch(x -> x == (reserve - 1)) && visited[reserve-1] == false) {
            visited[reserve - 1] = true;
            return;
        }

        if (IntStream.of(lost).anyMatch(x -> x == (reserve + 1)) && visited[reserve+1] == false) {
            visited[reserve + 1] = true;
            return;
        }
    }
}
