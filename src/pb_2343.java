import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2343 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[] video = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int lo = 0;
        int hi = 1_000_000_000;

        while (lo+1 < hi) {
            int mid = (lo + hi) / 2;

            int length = 0;
            int cnt = 0;
            boolean isSmall = false;

            for (int i = 0; i < n; i++) {
                if (video[i] > length) {
                    cnt++;
                    length = mid - video[i];
                    if (length < 0) {
                        isSmall = true;
                        break;
                    }
                } else {
                    length -= video[i];
                }
            }

            if (cnt > m || isSmall) lo = mid;
            else hi = mid;
        }
        bw.write(hi + "");
        bw.flush();
        bw.close();

    }
}
