package thisiscodingtest.sort;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        // 정렬 전
        System.out.println(Arrays.toString(array));

        for (int i = 1; i < array.length; i++) {
            int target = array[i]; // 비교 대상

            int j = i - 1;
            while (j >= 0 && target < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = target;
        }

        // 정렬 후
        System.out.println(Arrays.toString(array));
    }
}
