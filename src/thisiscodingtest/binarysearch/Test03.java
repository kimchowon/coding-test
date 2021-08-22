package thisiscodingtest.binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 기출문제01. 정렬된 배열에서 특정 수의 개수 구하기
 */
public class Test03 {
    public static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);

        int firstIndex = getFirstIndex(array, M, 0, array.length - 1);
        int lastIndex = getLastIndex(array, M, 0, array.length - 1);

        if (firstIndex == -1 || lastIndex == -1) {
            bw.write(-1 + "\n");
        } else {
            int answer = lastIndex - firstIndex + 1;
            bw.write(answer + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static int getFirstIndex(int[] array, int num, int start, int end) {
        int firstIndex = -1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (array[mid] == num) {
                if (checkValidation(mid - 1)) {
                    if (array[mid - 1] == num) {
                        end = mid - 1;
                    } else {
                        firstIndex = mid;
                        break;
                    }
                } else {
                    firstIndex = mid;
                    break;
                }
            } else if (array[mid] < num) {
                start = mid + 1;
            } else if (array[mid] > num) {
                end = mid - 1;
            }
        }
        return firstIndex;
    }

    public static int getLastIndex(int[] array, int num, int start, int end) {
        int lastIndex = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (array[mid] == num) {
                if (checkValidation(mid + 1)) {
                    if (array[mid + 1] == num) {
                        start = mid + 1;
                    } else {
                        lastIndex = mid;
                        break;
                    }
                } else {
                    lastIndex = mid;
                    break;
                }
            } else if (array[mid] > num) {
                end = mid - 1;
            } else if (array[mid] < num) {
                start = mid + 1;
            }
        }
        return lastIndex;
    }

    public static boolean checkValidation(int index) {
        if (index < 0 || index >= array.length) {
            return false;
        }
        return true;
    }
}
