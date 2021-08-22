package programmers.level2;

import java.util.*;
public class Test34 {
    public static void main(String[] args) {
        String[][] relation = {{"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}};

        solution(relation);


    }

    public static int solution(String[][] relation) {
        int answer = 0;
        Set<String> set = new HashSet<>();
        List<Integer> indexList = new ArrayList<>();

        // 유일성 검사
        for (int i=0; i < relation[0].length; i++) {
            boolean flag = true;
            for (int j=0; j < relation.length; j++) {
                if (!set.add(relation[j][i])) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer++;
            }else {
                indexList.add(i);
            }
        }

        if (indexList.size() <= 1) {
            return answer;
        }

        for (int i=2; i <= indexList.size(); i++) {
            // 조합
            int[] buckets = new int[i];
            combination(indexList, buckets, buckets.length);
        }






        return answer;
    }

    public static void combination(List<Integer> items, int[] buckets, int k) {
        if (k == 0) {
            for (int i=0; i < buckets.length; i++) {
                System.out.print(buckets[i] + " ");
            }
            System.out.println();
            return;
        }

        int lastIndex = buckets.length - k - 1;

        for (int i=0; i < items.size(); i++) {
            if (buckets.length == k) {
                buckets[0] = items.get(i);

                combination(items, buckets, k-1);
            }else {

                if (buckets[lastIndex] < items.get(i)) {
                    buckets[lastIndex+1] = items.get(i);
                    combination(items, buckets, k-1);
                }
            }
        }
    }
}
