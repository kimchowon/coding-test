package programmers.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test35 {
    public static void main(String[] args) {
        solution("ABABABABABABABAB");
    }

    public static int[] solution(String msg) {
        List<Integer> answerList = new ArrayList<>();

        // 사전 HashMap으로 구현
        // 사전 초기화
        HashMap<Integer, String> dictionary = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            dictionary.put(i, String.valueOf((char) (i + 64)));
        }

        // 단어를 순회할 index
        int index = 0;
        while (index < msg.length()) {
            // 단어의 해당 인덱스 문자
            String w = msg.substring(index, index + 1);

            // w를 시작으로 글자를 하나씩 늘려감
            for (int i = index + 1; i < msg.length(); i++) {
                w += msg.charAt(i);

                // 사전에 포함하고 있지 않는 단어까지 도착하면
                if (!dictionary.containsValue(w)) {
                    // 사전에 해당 단어 put
                    dictionary.put(dictionary.size() + 1, w);

                    // 단어에서 다시 마지막 글자를 제거
                    w = w.substring(0, w.length() - 1);
                    break;
                }
            }
            // answer에 단어 w add
            answerList.add(getKey(dictionary, w));

            // 다음에 순회할 index
            index = index + w.length();
        }

        int count = 0;
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[count++] = answerList.get(i);
        }
        return answer;
    }

    // hashMap에서 value로 key 찾기
    public static int getKey(HashMap<Integer, String> m, Object value) {
        for (int o : m.keySet()) {
            if (m.get(o).equals(value)) {
                return o;
            }
        }
        return 0;
    }
}
