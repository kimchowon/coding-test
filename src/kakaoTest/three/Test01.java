package kakaoTest.three;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 캐시
 * 소요시간: 21분 36초
 */
public class Test01 {
    public static void main(String[] args) {
        int cacheSize = 2;
        String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};

        int answer = solution(cacheSize, cities);
        System.out.println(answer);
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> cache = new LinkedList<>();

        // 캐시 크기가 0이면 항상 캐시 miss이므로 모든 수행시간이 5초임
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();

            if (!cache.contains(city)) { // 캐시 miss
                answer = answer + 5;
                if (cache.size() == cacheSize) {
                    cache.poll();
                }
            } else { // 캐시 hit
                answer = answer + 1;
                cache.remove(city);
            }
            cache.add(city);
        }
        return answer;
    }
}
