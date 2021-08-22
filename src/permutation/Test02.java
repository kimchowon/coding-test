package permutation;

/**
 * 중복 순열
 * - 뽑을 때마다 모든 아이템이 후보가 된다.
 */
public class Test02 {
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

        int lastIndex = buckets.length - k - 1;
        for (int i = 0; i < items.length; i++) {
            buckets[lastIndex + 1] = items[i];
            solution(items, buckets, k - 1);
        }
    }
}
