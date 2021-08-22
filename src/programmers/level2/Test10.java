package programmers.level2;

public class Test10 {
    public static int answer;

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println(solution(numbers, target));
    }

    public static int solution(int[] numbers, int target) {
        answer = 0;
        int[] items = {0, 1};
        int[] buckets = new int[numbers.length];

        duplicatePermutation(numbers, target, items, buckets, buckets.length);

        return answer;
    }

    public static void duplicatePermutation(int[] numbers, int target, int[] items, int[] buckets, int k) {
        if (k == 0) {
            int result = 0;
            for (int i = 0; i < numbers.length; i++) {
                if (buckets[i] == 0) {
                    result += numbers[i];
                } else {
                    result -= numbers[i];
                }
            }

            if (result == target) {
                answer++;
            }
            return;
        }

        int lastIndex = buckets.length - k - 1;

        for (int i = 0; i < items.length; i++) {
            buckets[lastIndex + 1] = items[i];
            duplicatePermutation(numbers, target, items, buckets, k - 1);
        }
    }
}
