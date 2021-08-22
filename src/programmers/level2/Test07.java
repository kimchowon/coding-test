package programmers.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test07 {

    public static void main(String[] args) {
        //int[] numbers = {3, 30, 34, 5, 9};
        //  int[] numbers = {6, 10, 2};

        int[] numbers = {0,0,0,0};

        System.out.println("result :" + Integer.parseInt("00"));
   //     System.out.println("answer : " + solution(numbers));




    }

    public static String solution(int[] numbers) {
        String answer = "";

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
        }

        Collections.sort(list, (a, b) -> {
            String as = String.valueOf(a);
            String bs = String.valueOf(b);

            System.out.println("뭐야 ?:" + Integer.parseInt(as+bs));

            return -Integer.compare(Integer.parseInt(as+bs), Integer.parseInt(bs+as));

        });

        for(int i =0; i < list.size(); i++) {
            answer += list.get(i);
        }

        if ((answer.charAt(0)+"").equals("0")) {
            return "0";
        }
        return answer;
    }
}
