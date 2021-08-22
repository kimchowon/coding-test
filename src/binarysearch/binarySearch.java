package binarysearch;

/**
 * 이분 탐색
 */
public class binarySearch {

    public static void main(String[] args) {

        int[] array = {1, 3, 5, 6, 7, 9, 11, 20, 30};
        int a = 30;

        System.out.println(searchBinary(array, a));
    }

    public static int searchBinary(int[] array, int a) {
        int first = 0;
        int last = array.length - 1;

        while (first <= last) {
            int mid = (first + last) / 2;

            if (array[mid] == a) {
                return mid;
            }

            if (a > array[mid]) {
                // 뒷부분만 다시 탐색
                first = mid + 1;
            } else {
                // 앞부분만 다시 탐색
                last = mid - 1;
            }
        }
        return -1;

    }
}
