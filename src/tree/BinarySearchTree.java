package tree;

public class BinarySearchTree {
    public static void main(String[] args) {
        root = new Node(30);

        insert(20);
        insert(40);
        insert(10);
        insert(25);
        insert(35);
        insert(45);

        preOrder(root);
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        /* 생성자 */
        public Node(int data) {
            this.setData(data);
            setLeft(null);
            setRight(null);
        }

        public int getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setData(int data) {
            this.data = data;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public static Node root;

    BinarySearchTree(){
        root = null;
    }

    public static boolean find(int data) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.data == data) {
                return true;
            }

            if (currentNode.data > data) {
                currentNode = currentNode.left;
            }else {
                currentNode = currentNode.right;
            }
        }
        return false;
    }

    /*삽입 연산*/
    public static void insert(int data) {
        Node newNode = new Node(data); // 왼쪽, 오른쪽 자식 노드가 null 이며 data 의 값이 저장된 새 노드 생성
        if(root == null){// 루트 노드가 없을때, 즉 트리가 비어있는 상태일 때,
            root = newNode;
            return;
        }
        Node currentNode = root;
        Node parentNode = null;

        while(true) {

            parentNode = currentNode;

            if(data < currentNode.getData()) { // 해당 노드보다 클 떄.
                currentNode = currentNode.getLeft();
                if(currentNode == null) {
                    parentNode.setLeft(newNode);
                    return ;
                }
            }else { // 해당 노드보다 작을 때.
                currentNode = currentNode.getRight();
                if(currentNode == null){
                    parentNode.setRight(newNode);
                    return ;
                }
            }
        }
    }

    /**
     * 전위 순회로 출력
     * @param root
     */
    public static void preOrder(Node root) {
        if (root!=null) {
            System.out.println(root.getData());
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }
}
