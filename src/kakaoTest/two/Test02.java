package kakaoTest.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test02 {
    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        int[] answer = solution(msg);
        System.out.println(Arrays.toString(answer));
    }

    public static HashMap<String, Integer> dictionaryMap = new HashMap<>();

    public static int[] solution(String msg) {
        List<Integer> answerList = new ArrayList<>();
        dictionaryMap = initDictionaryMap(); // 1. 사전 초기화

        while (true) {
            String w = getLongestWords(msg); // 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다
            answerList.add(dictionaryMap.get(w)); // w에 해당하는 사전의 색인 번호를 출력
            msg = removeW(w, msg); // 입력에서 w를 제거

            if (msg.length() == 0) { // w를 제거한 뒤 msg가 모두 제거되면 종료
                break;
            }

            String c = msg.charAt(0) + ""; // 입력에서 처리되지 않은 다음 글자
            String newWords = w + c;
            dictionaryMap.put(newWords, dictionaryMap.size() + 1); // 3. w+c에 해당하는 단어를 사전에 등록
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    public static HashMap<String, Integer> initDictionaryMap() {
        for (int i = 1; i <= 26; i++) {
            char c = (char) (i + 64);
            dictionaryMap.put(String.valueOf(c), i);
        }
        return dictionaryMap;
    }

    public static String getLongestWords(String curMsg) {
        for (int i = curMsg.length(); i >= 0; i--) {
            String s = curMsg.substring(0, i);
            if (dictionaryMap.containsKey(s)) {
                return s;
            }
        }
        return "";
    }

    public static String removeW(String w, String curMsg) {
        int wLength = w.length();
        curMsg = curMsg.substring(wLength);
        return curMsg;
    }
}
