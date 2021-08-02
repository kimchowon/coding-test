package kakao.blind2019;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 무지의 먹방 라이브
 */
public class Test02 {
    public static void main(String[] args) {
        int[] food_times = {3, 5, 1, 6, 5, 3};
        //  {3, 1, 2};
        long k =
                //    5;
                20;

        int answer = solution(food_times, k);
        System.out.println(answer);
    }

    public static class Food {
        int order; // 몇번째 음식인지
        int time; // 먹는데 필요한 시간

        public Food(int order, int time) {
            this.order = order;
            this.time = time;
        }
    }

    public static int solution(int[] food_times, long k) {
        List<Food> foodList = new LinkedList<>();
        for (int i = 0; i < food_times.length; i++) {
            foodList.add(new Food(i + 1, food_times[i]));
        }
        Collections.sort(foodList, Comparator.comparingInt(o -> o.time));

        int i = 0; // foodList의 현재 인덱스
        int foodCnt = foodList.size(); // 남아있는 음식 개수
        int preTime = 0;
        for (Food food : foodList) {
            long diff = food.time - preTime;
            if (diff > 0) {
                long spend = diff * foodCnt;
                if (k - spend >= 0) {
                    k -= spend;
                    preTime = food.time;
                } else {
                    k = k % foodCnt;
                    foodList.subList(i, foodList.size()).sort(Comparator.comparingInt(o -> o.order));
                    return foodList.get(i + (int) k).order;
                }
            }
            i++;
            foodCnt--;
        }
        return -1;
    }
}
