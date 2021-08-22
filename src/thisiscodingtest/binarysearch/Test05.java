package thisiscodingtest.binarysearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test05 {
    public static void main(String[] args) {
        String[] words = {"frodo", "frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        int[] answers = solution(words, queries);
        System.out.println(Arrays.toString(answers));
    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        // 중복 제거
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(words));
        words = new String[set.size()];
        words = set.toArray(words);
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length() >= 0 ? 1 : -1); // 길이로 정렬

        int index = 0;
        for (String query : queries) {
            int queryLen = query.length();

            int firstSameLenIndex = getFirstSameLenIndex(words, queryLen);

            if (firstSameLenIndex < 0) {
                answer[index] = 0;
            } else {
                int lastSameLenIndex = getLastSameLenIndex(words, queryLen);

                if (lastSameLenIndex < 0) {
                    answer[index] = 0;
                } else {
                    query = query.replaceAll("\\?", ".");
                    for (int i = firstSameLenIndex; i <= lastSameLenIndex; i++) {
                        if (words[i].matches(query)) {
                            answer[index]++;
                        }
                    }
                }
            }
            index++;

        }
        return answer;
    }

    public static int getFirstSameLenIndex(String[] words, int length) {
        int firstIndex = -1;

        int start = 0;
        int end = words.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (words[mid].length() == length) {
                if (checkValidation(mid - 1, words.length) && words[mid - 1].length() == length) {
                    end = mid - 1;
                } else {
                    firstIndex = mid;
                    break;
                }
            } else if (words[mid].length() > length) {
                end = mid - 1;
            } else if (words[mid].length() < length) {
                start = mid + 1;
            }
        }
        return firstIndex;
    }

    public static int getLastSameLenIndex(String[] words, int length) {
        int lastIndex = -1;

        int start = 0;
        int end = words.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (words[mid].length() == length) {
                if (checkValidation(mid + 1, words.length) && words[mid + 1].length() == length) {
                    start = mid + 1;
                } else {
                    lastIndex = mid;
                    break;
                }
            } else if (words[mid].length() > length) {
                end = mid - 1;
            } else if (words[mid].length() < length) {
                start = mid + 1;
            }
        }
        return lastIndex;
    }

    public static boolean checkValidation(int index, int wordsLen) {
        if (index < 0 || index >= wordsLen) {
            return false;
        }
        return true;
    }
}
