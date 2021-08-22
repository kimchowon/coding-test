package baekjoon.gold1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 11505 - 구간 곱 구하기
 */
public class Test10 {
    public static int h;
    public static int[] nums;
    public static long div = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간 곱을 구하는 회수

        int n = (int) Math.ceil(baseLog(N, 2));
        int pow = (int) Math.pow(2, n);
        nums = new int[pow + 1];

        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        for (int i = N + 1; i < nums.length; i++) {
            nums[i] = 1;
        }

        SegmentTree segmentTree = new SegmentTree(nums.length - 1);
        segmentTree.init(nums, 1, 1, nums.length - 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());

            switch (num1) {
                case 1: // 수 변경
                    // 마지막 리프노드의 개수
                    int leafNodeCnt = (int) Math.pow(2, h);
                    // 첫번째 리프노드 인덱스
                    int firstLeafNodeIndex = segmentTree.tree.length - leafNodeCnt;
                    // 변경이 일어난 리프노드의 인덱스
                    int idx = firstLeafNodeIndex + num2 - 1;
                    segmentTree.update(idx, num2, num2, num2, num3);
                    nums[num2] = num3;
                    break;
                case 2:
                    long multiple = segmentTree.multiple(1, 1, nums.length - 1, num2, num3);
                    bw.write(multiple + "\n");
                    break;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static double baseLog(double x, double base) {
        return Math.log10(x) / Math.log10(base);
    }

    static class SegmentTree {
        long tree[];
        int treeSize;

        SegmentTree(int arrSize) {
            h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

            this.treeSize = (int) Math.pow(2, h + 1);
            tree = new long[treeSize];
        }

        long init(int[] nums, int node_idx, int start, int end) {
            if (start == end) {
                return tree[node_idx] = nums[start] % div;
            }

            // 곱
            return tree[node_idx] = (init(nums, node_idx * 2, start, (start + end) / 2) % div) *
                    (init(nums, node_idx * 2 + 1, (start + end) / 2 + 1, end) % div) % div;
        }


        public void update(int node_idx, int start, int end, int idx, int updateNum) {
            // 만약 변경할 idx값이 범위 밖이면 해당트리는 확인 불필요
            if (idx < start || idx > end) return;
            if (node_idx <= 0) return;

            if (node_idx >= Math.pow(2, h)) {
                tree[node_idx] = updateNum;
            } else {
                tree[node_idx] = ((tree[node_idx * 2] % div) * (tree[node_idx * 2 + 1] % div)) % div;
            }

            update(node_idx / 2, start, end, idx, updateNum);
        }

        public long multiple(int node_idx, int start, int end, int left, int right) {
            // 범위를 벗어나게 되는 경우 더할 필요 없음
            if (left > end || right < start) {
                return 1;
            }

            // 범위내 완전히 포함시에는 더 내려가지 않고 바로 리턴
            if (left <= start && end <= right) {
                return tree[node_idx] % div;
            }

            // 그 외의 경우 좌/우측으로 지속 탐색 진행
            return ((multiple(node_idx * 2, start, (start + end) / 2, left, right) % div) *
                    (multiple(node_idx * 2 + 1, (start + end) / 2 + 1, end, left, right) % div)) % div;
        }
    }
}
