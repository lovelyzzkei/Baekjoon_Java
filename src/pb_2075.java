import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2075 {

    static int n;
    static PriorityQueue<Integer> pq;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int num : input) {
                pq.add(-num);
            }   
        }

        for (int i = 0; i < n-1; i++) {
            pq.poll();
        }

        bw.write(-pq.peek() + "");
        bw.flush();
        bw.close();

    }
}
