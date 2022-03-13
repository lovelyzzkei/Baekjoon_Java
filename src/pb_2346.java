import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2346 {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] value = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Deque<Integer> balloons = new ArrayDeque<>();
        int[] ans = new int[n];

        for (int i = 1; i <= n; i++) {
            balloons.add(i);
        }

        int cnt = 0;
        while (balloons.size() != 1) {
            int idx = balloons.poll();
            ans[cnt++] = idx;

            if (value[idx-1] > 0) {
                for (int i = 0; i < value[idx-1]-1; i++) {
                    balloons.add(balloons.poll());
                }
            }
            else {
                for (int i = 0; i > value[idx-1]; i--) {
                    balloons.addFirst(balloons.removeLast());
                }
            }
        }
        ans[cnt] = balloons.peek();

        for (Integer i : ans) {
            bw.write(i + " ");
        }
        bw.flush();
        bw.close();

    }
}
