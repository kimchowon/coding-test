package kakao.blind2021.level4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test01_2 {
    public static void main(String[] args) {
        int[] sales = {14, 17, 15, 18, 19, 14, 13, 16, 28, 17};
        int[][] links = {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}};
        solution(sales, links);
    }

    public static int DP[][];
    public static int[][] LINKS;
    public static int[] SALES;
    public static Map<Integer, List<Integer>> TEAM_MAP = new HashMap<>();

    public static int solution(int[] sales, int[][] links) {
        DP = new int[300001][2];
        LINKS = links;
        SALES = sales;
        int answer = 0;

        createTeamMap();
        getMinSales(1);
        return answer;
    }

    public static void createTeamMap() {
        for (int i = 0; i < LINKS.length; i++) {
            int[] cur = LINKS[i];
            int leader = cur[0];
            int member = cur[1];
            List<Integer> memberList = new ArrayList<>();
            if (TEAM_MAP.containsKey(leader)) {
                memberList = TEAM_MAP.get(leader);
            }
            memberList.add(member);
            TEAM_MAP.put(leader, memberList);
        }
    }

    public static void getMinSales(int empNo) {
        // 맨 밑줄 직원이면
        if (!TEAM_MAP.containsKey(empNo)) {
            DP[empNo][0] = 0; // 이 직원이 참석하지 않는 경우
            DP[empNo][1] = SALES[empNo - 1]; // 이 직원이 참석하는 경우
            return;
        }

        List<Integer> memberList = TEAM_MAP.get(empNo);
        for (int m : memberList) {
            getMinSales(m);
            if (DP[empNo][0] == 0) {
                DP[empNo][0] = DP[m][1];
            } else {
                DP[empNo][0] = Math.min(DP[empNo][0], DP[m][1]);
            }
            DP[empNo][1] = SALES[empNo - 1];
        }
    }
}
