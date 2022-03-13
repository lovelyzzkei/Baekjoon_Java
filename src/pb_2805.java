import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2805 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[] height = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(height);

        int lo = 0;
        int hi = 1_000_000_000;

        while (lo+1 < hi) {
            int mid = (lo + hi) / 2;

            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (height[i] > mid) sum += (height[i] - mid);
            }

            if (sum >= m) lo = mid;
            else hi = mid;
        }

        bw.write(lo + "");
        bw.flush();
        bw.close();

    }
}
