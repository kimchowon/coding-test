package graph;

/**
 * Disjoint Set(서로소 집합) 알고리즘
 */
public class DisjointSet {
    public static int[] parent = new int[10];

    public static void main(String[] args) {

        for (int i = 0; i < parent.length; i++) {
            makeSet(i);
        }

        printSet();

        unionSet(0,1); printSet();
        unionSet(3,5); printSet();
        unionSet(0,3); printSet();

    }

    public static void printSet() {
        System.out.print("index : " );
        for (int i=0; i < parent.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("parent : ");
        for (int i=0; i < parent.length; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * 초기화.
     * 자기 자신을 부모 노드로 가지는 새로운 집합 생성
     *
     * @param x
     */
    public static void makeSet(int x) {
        parent[x] = x;
    }

    /**
     * 원소 x가 포함된 집합의 대표값(루트 노드)를 찾는 연산
     * 원소 x가 어떤 집합에 포함는 되어 있는지를 알아내 연산
     *
     * @param x
     * @return
     */
    public static int findSet(int x) {
        if (x == parent[x]) {
            return x;
        }

        parent[x] = findSet(parent[x]);
        return parent[x];
    }

    /**
     * 원소x와 원소y의 집합을 합치기
     *
     * @param x
     * @param y
     */
    public static void unionSet(int x, int y) {
        int pX = findSet(x);
        int pY = findSet(y);

        if (pX != pY) {
            parent[pY] = pX;
        }
    }
}
