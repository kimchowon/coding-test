package thisiscodingtest.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {5, 7, 9, 0, 3, 1, 6, 2, 4, 8};

        System.out.println(Arrays.toString(array));

        quickSort(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = left;
        int l = left + 1;
        int r = right;

        while (l <= r) {
            while (l <= right && array[pivot] > array[l]) {
                l++;
            }

            while (r >= left && array[pivot] < array[r]) {
                r--;
            }

            int temp;
            if (l > r) {
                temp = array[pivot];
                array[pivot] = array[r];
            } else {
                temp = array[l];
                array[l] = array[r];
            }
            array[r] = temp;
        }

        quickSort(array, left, r - 1);
        quickSort(array, r + 1, right);
    }
}
