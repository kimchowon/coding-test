package kakao.blind2022;

import java.util.*;

public class Test01 {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

/*        String[] id_list = {"con", "ryan"};
        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k = 3;*/
        int[] answer = solution(id_list, report, k);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Set<String>> reportMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>(); // 각 이용자가 신고당한 횟수를 세는 map

        for (String r : report) {
            String[] strings = r.split(" ");
            String reporter = strings[0];
            String badUser = strings[1];

            if (!reportMap.containsKey(reporter)) {
                Set<String> badUserSet = new HashSet<>();
                badUserSet.add(badUser);
                reportMap.put(reporter, badUserSet);
                countMap.put(badUser, countMap.getOrDefault(badUser, 0) + 1);
            } else {
                Set<String> badUserSet = reportMap.get(reporter);
                if (badUserSet.add(badUser)) {
                    countMap.put(badUser, countMap.getOrDefault(badUser, 0) + 1);
                }
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            String reporter = id_list[i];

            if (!reportMap.containsKey(reporter)) {
                answer[i] = 0;
            } else {
                Set<String> badUserSet = reportMap.get(reporter);
                for (String badUser : badUserSet) {
                    if (countMap.get(badUser) >= k) {
                        answer[i]++;
                    }
                }
            }
        }
        return answer;
    }
}
