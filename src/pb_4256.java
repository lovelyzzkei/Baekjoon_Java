import java.io.*;
import java.util.*;

public class pb_4256 {

    static String[] preOrder;
    static String[] inOrder;

    static void makePostOrder(int start, int end, int idx) {
        for (int i = start; i < end; i++) {
            if (inOrder[i].equals(preOrder[idx])) {
                makePostOrder(start, i, idx+1);
                makePostOrder(i+1, end, i + idx + 1 - start);
                System.out.print(preOrder[idx] + " ");
            }
        }
        
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            preOrder = br.readLine().split(" ");
            inOrder = br.readLine().split(" ");

            makePostOrder(0, n, 0);
            System.out.println();
        }
       
        bw.flush();
        bw.close();

    }
}
