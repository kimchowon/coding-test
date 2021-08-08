package kakao.blind2019;

import java.util.*;

/**
 * 오픈 채팅방
 */
public class Test03 {
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] answer = solution(record);
        System.out.println(Arrays.toString(answer));
    }

    public static String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        for (String data : record) {
            String[] dataList = data.split(" ");
            if (dataList[0].equals("Enter") || dataList[0].equals("Change")) {
                map.put(dataList[1], dataList[2]);
            }
        }

        List<String> answerList = new ArrayList<>();
        for (int i=0; i < record.length; i++) {
            String status;
            String result;
            String[] dataList = record[i].split(" ");
            if (dataList[0].equals("Enter")) {
                status = "님이 들어왔습니다.";
            }else if (dataList[0].equals("Leave")) {
                status = "님이 나갔습니다.";
            }else {
                continue;
            }
            String id = dataList[1];
            String nickname = map.get(id);
            result = nickname + status;
            answerList.add(result);
        }

        String[] answer = new String[answerList.size()];
        for (int i=0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
