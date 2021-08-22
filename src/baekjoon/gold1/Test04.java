package baekjoon.gold1;

import java.io.*;
import java.util.StringTokenizer;

public class Test04 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int n = (int) Math.ceil(baseLog(N, 2));
        int pow = (int) Math.pow(2, n);
        int[] nums = new int[pow + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(nums.length - 1);
        segmentTree.init(nums, 1, 1, nums.length - 1);

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Node targetNode = segmentTree.getTargetNode(1, 1, nums.length - 1, start, end);
            bw.write(targetNode.min + " " + targetNode.max + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static class Node {
        int max;
        int min;

        Node(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    public static double baseLog(double x, double base) {
        return Math.log10(x) / Math.log10(base);
    }

    public static Node[] tree;

    static class SegmentTree {
        int treeSize;

        public SegmentTree(int arrSize) {
            // 트리의 높이
            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

            // 트리에 들어가는 노드의 개수
            this.treeSize = (int) Math.pow(2, h + 1);
            tree = new Node[treeSize];
        }

        public Node init(int[] nums, int node_idx, int start, int end) {
            if (start == end) {
                Node node = new Node(nums[start], nums[start]);
                return tree[node_idx] = node;
            }

            Node left = init(nums, node_idx * 2, start, (start + end) / 2);
            Node right = init(nums, node_idx * 2 + 1, (start + end) / 2 + 1, end);
            int max = Math.max(left.max, right.max);
            int min = Math.min(left.min, right.min);

            return tree[node_idx] = new Node(max, min);
        }

        public Node getTargetNode(int node_idx, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return null;
            }

            // 범위내 완전히 포함시에는 더 내려가지 않고 바로 리턴
            if (left <= start && end <= right) {
                return tree[node_idx];
            }

            Node lNode = getTargetNode(node_idx * 2, start, (start + end) / 2, left, right);
            Node rNode = getTargetNode(node_idx * 2 + 1, (start + end) / 2 + 1, end, left, right);

            if (lNode == null) {
                return rNode;
            }

            if (rNode == null) {
                return lNode;
            }

            return new Node(Math.max(lNode.max, rNode.max), Math.min(lNode.min, rNode.min));
        }
    }
}
