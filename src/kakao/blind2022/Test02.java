package kakao.blind2022;

import java.util.*;

public class Test02 {
    public static void main(String[] args) {
        int n = 2;
        int k = 5;
        // 211020101011

        int answer = solution(n, k);
        System.out.println(answer);
    }

    public static boolean[] primeNumberArray;
    public static int solution(int n, int k) {
        int answer = 0;
        List<Integer> numberList = new ArrayList<>();

        String N = getConventN(n, k);
        String number = "";
        for (char c : N.toCharArray()) {
            if (c != '0') {
                number += c;
            }

            if (c == '0') {
                // 0P0을 만족하는지 확인
                if (!number.equals("")) {
                    numberList.add(Integer.parseInt(number));
                }
                number = "";
            }
        }

        if (!number.equals("")) {
            numberList.add(Integer.parseInt(number));
        }

        numberList.sort(Integer::compare);
        int max = numberList.get(numberList.size()-1);
        primeNumberArray = new boolean[max+5];
        createPrimeNumberArray(max);

        for (int num : numberList) {
            if (primeNumberArray[num]) {
                answer++;
            }
        }
        return answer;
    }

    // 10진수를 N진수로 변환
    public static String getConventN(int num, int n) {
        String result = "";
        if (num == 0) {
            return "0";
        }

        while (num > 0) {
            int share = num / n;
            int remainder = num % n;
            if (remainder > 9) {
                result = (char) (remainder + 55) + result;
            } else {
                result = remainder + result;
            }
            num = share;
        }
        return result;
    }

    public static void createPrimeNumberArray(int num) {
        primeNumberArray[0] = primeNumberArray[1] = false;
        for(int i=2; i<=num; i+=1) {
            primeNumberArray[i] = true;
        }

        for(int i=2; i*i<=num; i+=1) {
            for(int j=i*i; j<=num; j+=i) {
                primeNumberArray[j] = false;
            }
        }
    }
}
