import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_6597 {

    static char[][] tree;
    static String preOrder, inOrder;

    // 루트가 root이고 [start, end] 구간에 대한 트리 생성
    static void makeTree(int start, int end, int root) {
        if (start == end) return;

        int mid = inOrder.indexOf(preOrder.charAt(root));
        if (start <= mid-1) {
            tree[preOrder.charAt(root)-'A'][0] = preOrder.charAt(root+1); 
            makeTree(start, mid-1, root+1);
        }
        if (mid+1 <= end) {
            tree[preOrder.charAt(root)-'A'][1] = preOrder.charAt(root+(mid+1-start)); 
            makeTree(mid+1, end, root+(mid+1-start));
        }
    }

    static String postOrder(char root) {
        if (root == '0') return "";
        String left = postOrder(tree[root-'A'][0]);
        String right = postOrder(tree[root-'A'][1]);
        return left + right + root;
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while (true) {
            String input = br.readLine();
            if (input == null || input.equals("")) break;
            
            preOrder = input.split(" ")[0];
            inOrder = input.split(" ")[1];

            tree = new char[26][2];
            for (char[] arr : tree) {
                Arrays.fill(arr, '0');
            }

            makeTree(0, preOrder.length()-1, 0);

            // for (char[] arr : tree) {
            //     System.out.print(Arrays.toString(arr) + " ");
            // }
            // System.out.println();

            bw.write(postOrder(preOrder.charAt(0)) + "\n");
        }
        
        bw.flush();
        bw.close();

    }
}
