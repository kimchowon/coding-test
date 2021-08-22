package baekjoon.study;

public class SieveofEratosthenes {
    public static void main(String[] args) {
        int num = 25;
        printPrimeNumber(num);
    }

    public static void printPrimeNumber(int num) {

        // 배열에 원소 삽입
        int[] array = new int[num + 1];
        for (int i = 2; i <= num; i++) {
            array[i] = i;
        }

        for (int i = 2; i <= num; i++) {
            // 배열이 이미 0이면 건너뜀
            if (array[i] == 0) {
                continue;
            }

            // i의 배수인 원소들을 모두 지워줌
            for (int j = i * i; j <= num; j += i) {
                array[j] = 0;
            }
        }

        // 0이 아닌(지워지지 않은) 숫자들만 출력
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                System.out.print(array[i] + " ");
            }
        }
    }
}
