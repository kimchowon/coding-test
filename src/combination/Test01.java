package combination;

/**
 * 조합
 */
public class Test01 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] items = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] bucket = new int[4];
        solution(items, bucket, bucket.length);
    }

    public static void solution(int[] items, int[] buckets, int k) {
        if (k == 0) {
            for (int i = 0; i < buckets.length; i++) {
                System.out.print(buckets[i] + " ");
            }
            System.out.println();
            return;
        }

        int lastIndex = buckets.length - k - 1; // -1부터 시작
        for (int i = 0; i < items.length; i++) {

            if (buckets.length == k) {
                buckets[0] = items[i];
                solution(items, buckets, k - 1);
            } else if (buckets[lastIndex] < items[i]) {
                buckets[lastIndex + 1] = items[i];
                solution(items, buckets, k - 1);

            }
        }
    }
}
