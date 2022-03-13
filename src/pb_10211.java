import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_10211 {

    static int n;
    static int[] x;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            x = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int maxSubHeading = x[0];
            int ret = x[0];
            for (int i = 1; i < n; i++) {
                maxSubHeading = Math.max(maxSubHeading + x[i], x[i]);
                if (maxSubHeading > ret) {
                    ret = maxSubHeading;
                }
            }
            bw.write(String.format("%d\n", ret));

        }

        bw.flush();
        bw.close();

    }
}
