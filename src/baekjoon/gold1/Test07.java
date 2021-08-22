package baekjoon.gold1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Test07 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int n = (int)Math.ceil(baseLog(N, 2));
        int pow = (int)Math.pow(2, n);
        int[] nums = new int[pow + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        for (int i = N+1; i < nums.length; i++) {
            nums[i] = Integer.MAX_VALUE;
        }

        SegmentTree segmentTree = new SegmentTree(nums.length-1);
        segmentTree.init(nums, 1, 1, nums.length - 1);

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            long answer = segmentTree.getMin(1, 1, nums.length - 1, start, end);
            bw.write(answer + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static class SegmentTree {
        long tree[];
        int treeSize;

        SegmentTree(int arrSize) {
            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

            this.treeSize = (int) Math.pow(2, h + 1);
            tree = new long[treeSize];
        }

        long init(int[] nums, int node_idx, int start, int end) {
            if (start == end) {
                return tree[node_idx] = nums[start];
            }

            return tree[node_idx] = Math.min(init(nums, node_idx * 2, start, (start + end) / 2),
                    init(nums, node_idx * 2 + 1, (start + end) / 2 + 1, end));
        }

        long getMin(int node_idx, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return Integer.MAX_VALUE;
            }

            if (left <= start && end <= right) {
                return tree[node_idx];
            }

            return Math.min(getMin(node_idx * 2, start, (start + end) / 2, left, right),
                    getMin(node_idx * 2 + 1, (start + end) / 2 + 1, end, left, right));
        }
    }

    public static double baseLog(double x, double base) {
        return Math.log10(x) / Math.log10(base);
    }
}
