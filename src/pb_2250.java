import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2250 {

    static int n;
    static int[] nodeX;
    static int[][] tree, numOfSubTree;

    static int calcSubTree(int curr) {
        
        int left = tree[curr][0];
        int right = tree[curr][1];

        // left subtree 계산
        if (numOfSubTree[curr][0] == 0 && left != -1) {
            numOfSubTree[curr][0] = 1 + calcSubTree(left);
        }

         // right subtree 계산
        if (numOfSubTree[curr][1] == 0 && right != -1) {
            numOfSubTree[curr][1] = 1 + calcSubTree(right);
        }

        return numOfSubTree[curr][0] + numOfSubTree[curr][1];
    }

    static void calcCoorX(int curr) {
        int left = tree[curr][0];
        int right = tree[curr][1];

        if (left != -1) {
            nodeX[left] = nodeX[curr] - numOfSubTree[left][1] - 1;
            calcCoorX(left);
        }
        if (right != -1) {
            nodeX[right] = nodeX[curr] + numOfSubTree[right][0] + 1;
            calcCoorX(right);
        }

        return;
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());

        // 이차원 배열에 트리 정보를 저장
        tree = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            int node = Integer.parseInt(input[0]);
            int left = Integer.parseInt(input[1]);
            int right = Integer.parseInt(input[2]);

            tree[node][0] = left;
            tree[node][1] = right;
        }

        // 루트 찾기
        // numOfSubTree에 각 노드의 left와 right subtree에 있는 노드들의 개수를 저장
        int root = 0;
        numOfSubTree = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            if (calcSubTree(i) + 1 == n) {
                root = i;
            }
        }


        nodeX = new int[n+1];
        calcCoorX(root);

        // BFS를 하며 각 level마다 너비를 계산. 그 중에 최대 너비를 구함
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(root);

        int level = 1;
        int maxLevel = 1;
        int maxWidth = 1;

        while (!queue.isEmpty()) {

            int leftX = 10001;
            int rightX = -10001;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int front = queue.poll();
                int left = tree[front][0];
                int right = tree[front][1];

                leftX = Math.min(leftX, nodeX[front]);
                rightX = Math.max(rightX, nodeX[front]);

                if (left != -1) queue.add(left);
                if (right != -1) queue.add(right);
            }

            int cand = rightX - leftX + 1;
            if (cand > maxWidth) {
                maxWidth = cand;
                maxLevel = level;
            }
            level++;
        }
        
        bw.write(maxLevel + " " + maxWidth);
        bw.flush();
        bw.close();

    }
}
