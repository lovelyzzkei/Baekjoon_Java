import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_1158 {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        LinkedList<Integer> queue = new LinkedList<Integer>();
        LinkedList<Integer> ans = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        int idx = k-1;
        while (queue.size() != 0) {
            if (idx == 0) {
                ans.add(queue.poll());
                idx = k-1;
            }
            else {
                queue.add(queue.poll());
                idx--;
            }
        }

        String ansToString = ans.toString();
        
        bw.write("<" + ansToString.substring(1,ansToString.length()-1) + ">");
        bw.flush();
        bw.close();

    }
}
