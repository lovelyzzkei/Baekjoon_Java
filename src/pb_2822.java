import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2822 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b)->(b[0] - a[0]));
        for (int i = 1; i <= 8; i++) {
            int score = Integer.parseInt(br.readLine());
            pq.add(new Integer[] {score, i});
        }

        int sum = 0;
        int[] ans = new int[5];
        for (int i = 0; i < 5; i++) {
            Integer[] curr = pq.poll();
            sum += curr[0];
            ans[i] = curr[1];
        }

        Arrays.sort(ans);
        
        bw.write(sum+"\n");
        for (int n : ans) {
            bw.write(n + " ");
        }

        bw.flush();
        bw.close();

    }
}
