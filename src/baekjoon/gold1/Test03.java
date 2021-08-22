package baekjoon.gold1;

import java.io.*;
import java.util.StringTokenizer;

public class Test03 {
    // 세그먼트 트리를 만들 내부 클래스
    static class SegmentTree {
        long tree[];
        int treeSize;

        public SegmentTree(int arrSize) {
            // 트리의 높이
            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

            // 트리에 들어가는 노드의 개수
            this.treeSize = (int) Math.pow(2, h + 1);
            tree = new long[treeSize];
        }

        // 최초 세그먼트 그리 구성하기
        public long init(long[] nums, int node_idx, int start, int end) {
            // start==end는 리프노드라는 의미
            // 즉, 배열의 값을 그대로 저장함
            if (start == end) {
                return tree[node_idx] = nums[start];
            }

            // 위에서 return 못했다면 start!=end
            // 그렇다면 좌측노드와 우축노드의 합으로 구해짐
            // 좌측노드는 *2, 우측노드는 *2+1
            return tree[node_idx] = init(nums, node_idx * 2, start, (start + end) / 2)
                    + init(nums, node_idx * 2 + 1, (start + end) / 2 + 1, end);
        }

        public void update(int node_idx, int start, int end, int idx, long diff) {
            // 만약 변경할 idx값이 범위 밖이면 해당트리는 확인 불필요
            if (idx < start || idx > end) return;

            // 변경된 차이만큼 영향받는 노드에 더해줌.
            tree[node_idx] += diff;

            // 리프노드에 다다르기까지 모든 노드의 값을 바꿔야하므로 지속 진행
            if (start != end) {
                update(node_idx * 2, start, (start + end) / 2, idx, diff);
                update(node_idx * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
            }
        }

        public long sum(int node_idx, int start, int end, int left, int right) {
            // 범위를 벗어나게 되는 경우 더할 필요 없음
            if (left > end || right < start) {
                return 0;
            }

            // 범위내 완전히 포함시에는 더 내려가지 않고 바로 리턴
            if (left <= start && end <= right) {
                return tree[node_idx];
            }

            // 그 외의 경우 좌/우측으로 지속 탐색 진행
            return sum(node_idx * 2, start, (start + end) / 2, left, right) +
                    sum(node_idx * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long updateNum = Long.parseLong(st.nextToken());
        long sumNum = Long.parseLong(st.nextToken());

        int count = 0;
        while ((N + count) % 4 > 0) {
            count++;
        }

        long[] nums = new long[N + count + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(nums.length - 1);
        segmentTree.init(nums, 1, 1, nums.length - 1);

        for (int i = 0; i < updateNum + sumNum; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type==1) {
                int num1 = Integer.parseInt(st.nextToken());
                long num2 = Long.parseLong(st.nextToken());
                long diff = num2 - nums[num1];
                nums[num1] = num2;
                segmentTree.update(1, 1, nums.length - 1, num1, diff);
            }else if (type==2) {
                int num3 = Integer.parseInt(st.nextToken());
                int num4 = Integer.parseInt(st.nextToken());
                long sum = segmentTree.sum(1, 1, nums.length - 1, num3, num4);
                bw.write(sum+"\n");
            }else {
                return;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
