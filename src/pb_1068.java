import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1068 {

    static int n, eraseNode;
    static int[] parent;
    static ArrayList<ArrayList<Integer>> tree;

    static int search(int curr) {
        int leaf = 0;
        boolean hasChild = false;
        for (int next : tree.get(curr)) {
            if (next != eraseNode) {
                hasChild = true;
                leaf += search(next);
            }
        }

        if (!hasChild) return 1;
        else return leaf;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        parent = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (parent[i] == -1) root = i;
            else {
                tree.get(parent[i]).add(i);
            }
        }

        eraseNode = Integer.parseInt(br.readLine());
        int numOfLeafNode = 0;
        if (root != eraseNode) {
            numOfLeafNode = search(root);
        }
       
        bw.write(numOfLeafNode + "");
        bw.flush();
        bw.close();

    }
}
