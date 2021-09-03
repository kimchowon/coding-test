package kakao.blind2021.level2;

import java.util.*;

public class Test02 {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        int[] answer = solution(info, query);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // 1.map 만들기
        Map<String, List<Integer>> scoreMap = new HashMap<>();
        for (int m = 0; m < info.length; m++) {
            String[] infoStrings = info[m].split(" ");
            String language[] = {"-", infoStrings[0]};
            String job[] = {"-", infoStrings[1]};
            String career[] = {"-", infoStrings[2]};
            String food[] = {"-", infoStrings[3]};
            int score = Integer.parseInt(infoStrings[4]);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        for (int l = 0; l < 2; l++) {
                            String key = language[i] + job[j] + career[k] + food[l];

                            List<Integer> scoreList = new ArrayList<>();
                            if (scoreMap.containsKey(key)) {
                                scoreList = scoreMap.get(key);
                            }
                            scoreList.add(score);
                            scoreMap.put(key, scoreList);
                        }
                    }
                }
            }
        }

        for (String key : scoreMap.keySet()) {
            List<Integer> scoreList = scoreMap.get(key);
            Collections.sort(scoreList);
            scoreMap.put(key, scoreList);
        }

        for (int m = 0; m < query.length; m++) {
            String[] q = query[m].replaceAll(" and ", " ").split(" ");
            String key = q[0] + q[1] + q[2] + q[3];
            int score = Integer.parseInt(q[4]);

            if (!scoreMap.containsKey(key)) {
                answer[m] = 0;
                continue;
            }

            List<Integer> scores = scoreMap.get(key);
            int index = searchBinary(scores, score);
            int count = scores.size() - index;
            answer[m] = count;
        }
        return answer;
    }

    public static int searchBinary(List<Integer> scores, int score) {
        int start = 0;
        int end = scores.size();

        while (start < end) {
            int mid = (start + end) / 2;
            if (scores.get(mid) >= score) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
