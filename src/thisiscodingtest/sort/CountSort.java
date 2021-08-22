package thisiscodingtest.sort;

import java.util.Arrays;

public class CountSort {
    public static void main(String[] args) {
        int[] array = {7,5,9,0,3,1,6,2,9,1,4,8,0,5,2};

        System.out.println(Arrays.toString(array));

        int[] countArray = new int[10];
        for (int i=0; i < array.length; i++) {
            int index = array[i];
            countArray[index]++;
        }

        for (int i=0; i < countArray.length; i++) {
            for (int j=0; j < countArray[i]; j++) {
                System.out.print(i + " ");
            }
        }
    }
}
