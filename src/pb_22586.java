import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_22586 {

    static int n;
    static int[][] tree;
    static boolean[] visited;
    static boolean isFinished = false;

    static int inOrder(int curr) {
        int left = tree[curr][0];
        int right = tree[curr][1];

        int ret = -1;
        if (left != -1) ret = inOrder(left);
        ret = curr;
        if (right != -1) ret = inOrder(right);
        return ret;
    }

    static int similarInOrder(int curr, int last) {
        int left = tree[curr][0];
        int right = tree[curr][1];

        int move = 0;
        if (left != -1) {
            move += (1 + similarInOrder(left,last));
            if (isFinished) {
                return move;
            }
        }
        if (right != -1) {
            move += (1 + similarInOrder(right,last));
            if (isFinished) {
                return move;
            }

        }
        if (curr == last) {
            isFinished = true;
            return move;
        }
    
        return move+1;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        tree = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            int parent = Integer.parseInt(input[0]);
            int left = Integer.parseInt(input[1]);
            int right = Integer.parseInt(input[2]);

            tree[parent][0] = left;
            tree[parent][1] = right;
        }

        int root = 1;
        visited = new boolean[n+1];
        visited[root] = true;

        int last = inOrder(root);
        int ret = similarInOrder(root, last);
        bw.write(ret + "");



        
        bw.flush();
        bw.close();

    }
}
