package programmers.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test14 {
    public static int[][] answer = {};
    public static List<Integer> preOrderList = new ArrayList<>(); // 전위 순회 리스트
    public static List<Integer> postOrderList = new ArrayList<>(); // 후위 순회 리스트

    public static void main(String[] args) {
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        int[][] result = solution(nodeinfo);
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }

    public static class Data {
        int num; // nodeinfo 배열에서 몇번째인지 index 값
        int x;
        int y;

        public Data(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    public static class Node {
        private Data data; // 실질적인 데이터가 들어가는 필드
        private Node left; // 노드의 왼쪽 서브 트리
        private Node right; // 노드의 오른쪽 서브 트리

        /**
         * 생성자
         * @param data
         */
        public Node(Data data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node root = null;

    public static int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];

        // dataList 생성
        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            Data data = new Data(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
            dataList.add(data);
        }

        // y값 기준으로 dataList 정렬
        Collections.sort(dataList, (o1, o2) -> {
            if (o1.y == o2.y) {
                return o1.x - o2.x;
            }
            return o2.y - o1.y;
        });

        // 이진 탐색 트리 생성
        for (Data data : dataList) {
            insertNode(data);
        }

        preOrder(root); // 전위 순회
        postOrder(root); // 후위 순회

        for (int i = 0; i < preOrderList.size(); i++) {
            answer[0][i] = preOrderList.get(i);
            answer[1][i] = postOrderList.get(i);
        }
        return answer;
    }

    /**
     * 삽입 연산 메소드
     * @param data
     */
    public static void insertNode(Data data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
            return;
        }

        Node currentNode = root;
        Node parentNode;
        while (true) {
            parentNode = currentNode;

            if (data.x < currentNode.data.x) {
                currentNode = currentNode.left;
                if (currentNode == null) {
                    parentNode.left = newNode;
                    return;
                }
            } else {
                currentNode = currentNode.right;
                if (currentNode == null) {
                    parentNode.right = newNode;
                    return;
                }
            }
        }
    }

    /**
     * 전위 순회 (VLR)
     * @param root
     */
    public static void preOrder(Node root) {
        if (root != null) {
            preOrderList.add(root.data.num);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 후위 순회(LRV)
     * @param root
     */
    public static void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            postOrderList.add(root.data.num);
        }
    }
}
