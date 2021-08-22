package programmers.level2;

import java.util.LinkedList;
import java.util.Queue;

public class Test33 {
    public static void main(String[] args) {
        String[] cities = // {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
                // {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
                // {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
                // {"Jeju", "Pangyo", "NewYork", "newyork"};
                {"Jeju", "Jeju", "Jeju", "Jeju", "Jeju"};

        System.out.println(solution(5, cities));

    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> cache = new LinkedList<>();

        for (int i = 0; i < cities.length; i++) {

            String city = cities[i].toLowerCase();

            if (cache.contains(city)) {
                cache.remove(city);
                cache.add(city);

                answer = answer + 1;
            } else {

                if (cacheSize == 0) {
                    answer = answer + 5;
                    continue;
                }

                if (cache.size() == cacheSize) {
                    cache.poll();
                }
                answer = answer + 5;
                cache.add(city);
            }
        }
        return answer;
    }
}
