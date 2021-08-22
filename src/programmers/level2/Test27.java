package programmers.level2;

public class Test27 {
    public static int answer = 0;
    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 6, 4};
        System.out.println("answer : " + solution(nums));
    }

    public static int solution(int[] nums) {
        answer = 0;

        int[] buckets = new int[3];
        combination(nums, buckets, buckets.length);

        return answer;
    }

    public static void combination(int[] itmes, int[] buckets, int k) {
        if (k == 0) {
            boolean isPrime = isPrime(buckets);
            if (isPrime) {
                answer++;
            }
            return;
        }

        int lastIndex = buckets.length - k - 1;

        for (int i = 0; i < itmes.length; i++) {
            if (buckets.length == k) {
                buckets[0] = itmes[i];
                combination(itmes, buckets, k - 1);
            } else if (buckets[lastIndex] < itmes[i]) {
                buckets[lastIndex + 1] = itmes[i];
                combination(itmes, buckets, k - 1);
            }
        }
    }

    public static boolean isPrime(int[] nums) {
        int sum = 0;
        for (int i=0; i < nums.length; i++) {
            sum += nums[i];
        }
        for (int i=2; i < sum; i++) {
            if (sum % i == 0) {
                return false;
            }
        }

        return true;
    }

}
