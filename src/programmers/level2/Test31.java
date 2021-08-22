package programmers.level2;

import java.util.Arrays;
import java.util.HashMap;

public class Test31 {
    public static void main(String[] args) {

        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
             /*   {"hello", "observe", "effect", "take", "either", "recognize",
                        "encourage", "ensure", "establish", "hang", "gather",
                        "refer", "reference", "estimate", "executive"};*/

        //   {"hello", "one", "even", "never", "now", "world", "draw"};

        System.out.println("answer : " + Arrays.toString(solution(n, words)));

    }

    public static int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int index = 0;
        HashMap<String, Integer> map = new HashMap<>();

        // 가장 첫번째 단어
        String curW = words[0];

        // 첫번째 단어의 길이가 1인 경우
        if (curW.length() == 1) {
            index = 1;
        } else {
            // 첫번째 단어부터 map에 put
            map.put(curW, 1);

            // 두번째 단어부터 for loop
            for (int i = 1; i < words.length; i++) {
                curW = words[i];

                // 1. 단어의 길이가 1인 경우
                if (curW.length() == 1) {
                    index = i + 1;
                    break;
                }


                // 2. 단어의 첫번째 문자가 이전 단어의 마지막 문자가 아닌 경우
                if (!curW.startsWith(words[i - 1].substring(words[i - 1].length() - 1))) {
                    index = i + 1;
                    break;
                }

                // 3. 이전에 나왔던 단어를 또 말한 경우
                if (map.containsKey(curW)) {
                    index = i + 1;
                    break;
                } else {
                    map.put(curW, 1);
                }
            }
        }

        // 만약 index가 0이면 틀린 사람이 아무도 없는 것이므로 {0,0} 리턴
        if (index == 0) {
            return new int[]{0, 0};
        }

        answer[0] = index % n == 0 ? n : index % n;
        answer[1] = index % n == 0 ? index / n : (index / n) + 1;

        return answer;
    }
}
