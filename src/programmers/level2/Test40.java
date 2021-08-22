package programmers.level2;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test40 {
    public static int min = Integer.MAX_VALUE;
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(solution(begin, target, words));
    }

    /**
     *
     * @param begin
     * @param target
     * @param words
     * @return
     */
    public static int solution(String begin, String target, String[] words) {
        List<String> wordList = Arrays.asList(words);
        if (!wordList.contains(target)) {
            return 0;
        }

      //  int answer = getMinStepCount(begin, target, wordList);
        boolean[] confirmed = new boolean[wordList.size()];
        getMinStepCountForDfs(begin, target,  wordList, 0, confirmed);
        return min;
    }

    /**
     * bfs로 풀이
     * @param being
     * @param target
     * @param wordList
     * @return
     */
    public static int getMinStepCount(String being, String target, List<String> wordList) {
        int count = 0;
        String SEPERATOR = "/";
        Queue<String> q = new LinkedList<>();
        boolean[] confirmed = new boolean[wordList.size()];

        for (int i = 0; i < wordList.size(); i++) {
            String s = wordList.get(i);
            if (isDiffOnlyOneChar(being, s)) {
                q.add(s);
                confirmed[i] = true;
            }
        }
        count++;
        q.add(SEPERATOR);

        while (!q.isEmpty()) {
            String s = q.poll();

            if (s.equals(target)) {
                return count;
            }

            if (s.equals(SEPERATOR)) {
                count++;
                q.add(SEPERATOR);
            } else {
                for (int i = 0; i < wordList.size(); i++) {
                    String w = wordList.get(i);
                    if (!confirmed[i] && isDiffOnlyOneChar(s, w)) {
                        confirmed[i] = true;
                        q.add(w);
                    }
                }
            }
        }
        return count;
    }

    public static void getMinStepCountForDfs(String begin, String target, List<String> wordList,int count,  boolean[] confirmed) {

        if (begin.equals(target)) {
            min = Math.min(min, count);
        }
        
        for (int i=0; i < wordList.size(); i++) {
            String s = wordList.get(i);
            if (!confirmed[i] && isDiffOnlyOneChar(begin, s)) {
                confirmed[i] = true;
                getMinStepCountForDfs(s, target,wordList, count+1, confirmed);
                confirmed[i] = false;
            }
        }
    }

    /**
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isDiffOnlyOneChar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {

            if (count == 0 && s1.charAt(i) != s2.charAt(i)) {
                count++;
                continue;
            }

            if (count > 0 && s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }

        return count == 1 ? true : false;
    }
}
