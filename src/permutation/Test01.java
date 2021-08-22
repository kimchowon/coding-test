package permutation;

/**
 * 순열
 */
public class Test01 {
    public static void main(String[] args) {
// TODO Auto-generated method stub
        int[] items = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] bucket = new int[4];
        solution(items, bucket, bucket.length);
    }

    public static void solution(int[] items, int[] bucket, int k) {

        if (k == 0) { //다뽑음
            for (int i = 0; i < bucket.length; i++)
                System.out.print(bucket[i] + "");
            System.out.println();
            return;
        }
        int lastIndex = bucket.length - k - 1;
        for (int i = 0; i < items.length; i++) {
            int flag = 0;
            for (int j = 0; j <= lastIndex; j++) {
                if (bucket[j] == items[i])
                    flag = 1;
            }

            if (flag == 1)
                continue;

            bucket[lastIndex + 1] = items[i];
            solution(items, bucket, k - 1);
        }
    }

}
