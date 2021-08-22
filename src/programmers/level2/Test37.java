package programmers.level2;

import java.util.*;

public class Test37 {
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        System.out.println(Arrays.toString(solution(record)));

    }

    public static String[] solution(String[] record) {
        String[] answer;
        String[] statusList = new String[]{"Enter", "Leave", "Change"};

        Map<String, String> user = new HashMap<>();
        for (int i = 0; i < record.length; i++) {
            String[] strings = record[i].split(" ");

            if (strings[0].equals(statusList[0]) || strings[0].equals(statusList[2])) {
                user.put(strings[1], strings[2]);
            }
        }

        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < record.length; i++) {
            String[] strings = record[i].split(" ");
            String result = user.get(strings[1]) + "님이 ";

            if (strings[0].equals(statusList[0])) {
                result += "들어왔습니다.";
            } else if (strings[0].equals(statusList[1])) {
                result += "나갔습니다.";
            } else {
                continue;
            }
            resultList.add(result);
        }

        answer = new String[resultList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = resultList.get(i);
        }
        return answer;
    }
}
