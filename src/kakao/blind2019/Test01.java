package kakao.blind2019;

import java.util.*;

/**
 * 길 찾기 게임
 * 소요 시간:
 */
public class Test01 {

    public static void main(String[] args) {
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        int[][] answer = solution(nodeinfo);

        for (int i = 0; i < answer.length; i++) {
            System.out.println(Arrays.toString(answer[i]));
        }
    }

    public static class Data {
        int num;
        int x;
        int y;

        public Data(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    public static class Node {
        private Data data;
        private Node left;
        private Node right;

        public Node(Data data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node root = null;
    public static List<Integer> preOrderList = new ArrayList<>(); // 전위순회 리스트
    public static List<Integer> postOrderList = new ArrayList<>(); // 후외순회 리스트

    public static int[][] solution(int[][] nodeinfo) {
        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            Data data = new Data(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
            dataList.add(data);
        }

        Arrays.sort(nodeinfo, (o1, o2) -> {
            int result = Integer.compare(o1[1], o2[1]);
            if (result == 0) {
                return Integer.compare(o1[0], o2[0]);
            }else {
                return  -result;
            }
        });

        Collections.sort(dataList, (o1, o2) -> {
            if (o1.y == o2.y) {
                return o1.x - o2.x;
            }
            return o2.y - o1.y;
        });

        for (Data data : dataList) {
            insertNode(data);
        }

        // 전위 순회
        preOrder(root);
        // 후위 순회
        postOrder(root);

        int[][] answer = new int[2][preOrderList.size()];
        for (int i = 0; i < preOrderList.size(); i++) {
            answer[0][i] = preOrderList.get(i);
            answer[1][i] = postOrderList.get(i);
        }

        return answer;
    }

    // 트리에 노드 삽입
    public static void insertNode(Data data) {
        Node newNode = new Node(data); // 노드 생성

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

    // 전위 순회 (V -> L -> R)
    public static void preOrder(Node root) {
        if (root != null) {
            preOrderList.add(root.data.num);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    // 후위 순회(L -> R -> V)
    public static void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            postOrderList.add(root.data.num);
        }
    }
}
