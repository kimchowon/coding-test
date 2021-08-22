package baekjoon.gold5;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1759 - 암호 만들기
 */
public class Test06 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[] items = new char[C];
        char[] buckets = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            items[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(items);
        getPasswords(items, buckets, buckets.length);
        br.close();
        bw.flush();
        bw.close();
    }

    public static void getPasswords(char[] items, char[] buckets, int k) {
        if (k == 0) {
            if (isSatisfyCondition(buckets)) {
                for (int i = 0; i < buckets.length; i++) {
                    System.out.print(buckets[i]);
                }
                System.out.println();
            }
            return;
        }

        int lastIndex = buckets.length - k - 1;
        for (int i = 0; i < items.length; i++) {
            if (buckets.length == k) {
                buckets[0] = items[i];
                getPasswords(items, buckets, k - 1);
            } else {
                if (buckets[lastIndex] < items[i]) {
                    buckets[lastIndex + 1] = items[i];
                    getPasswords(items, buckets, k - 1);
                }
            }
        }
    }

    public static boolean isSatisfyCondition(char[] buckets) {
        int count = 0;
        String str = String.valueOf(buckets);

        // 1.모음이 한 개 이상 있는지
        String[] vowel = {"a", "e", "i", "o", "u"};
        for (String v : vowel) {
            if (str.indexOf(v) >= 0) {
                count++;
            }
        }

        if (count == 0) {
            return false;
        }

        // 2.자음이 2개 이상 있는지
        if (str.length() - count < 2) {
            return false;
        }
        return true;
    }
}
