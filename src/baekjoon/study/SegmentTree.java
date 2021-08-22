package baekjoon.study;

import java.io.*;
import java.util.*;

public class SegmentTree {
    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 수의 개수
        int M = Integer.parseInt(st.nextToken());   // 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken());   // 구간 합을 구하는 횟수
        arr = new long[N];
        tree = new long[4 * N];

        // 정수 입력 받기
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(0, arr.length - 1, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {  // 변경
                long diff = c - arr[b - 1];
                update(0, arr.length - 1, 1, b - 1, diff);
            } else if (a == 2) { // 구간 합
                bw.write(sum(0, arr.length - 1, 1, b - 1, c - 1) + "\n");
            } else {
                return;
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }

    // start, end: 원래 배열의 시작, 끝 인덱스
    // node: segment tree 의 주소
    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        }
    }

    // left, right: 구해야하는 구간 합 범위
    static long sum(int start, int end, int node, int left, long right) {
        if (left > end || right < start) {  // 범위 밖에 있는 경우
            return 0;
        }
        if (left <= start && end <= right) { // 범위 안에 속하는 경우
            return tree[node];
        }
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    // index: 원래 배열에서 바꾸려는 자리
    // diff: 원래 값과 바꾸려는 값의 차이
    static void update(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) { // 범위 밖에 있는 경우
            return;
        }
        // 범위 안에 있는 경우
        tree[node] += diff; // 우선 트리의 값을 바꿈
        if (start == end) { // leaf 노드인 경우
            arr[index] = tree[node];    // diff 계산할 때 arr의 값을 참고하기 때문에 arr의 값도 바꿔줘야함
            return;
        }
        // 하위에 있는 노드도 바꿔줌
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }
}