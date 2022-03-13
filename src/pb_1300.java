import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_1300 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long lo = 0;
        long hi = (int) Math.pow(n, 2);

        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;

            long cnt = 0;
            for (int row = 1; row <= n; row++) {
                cnt += ((mid / row) <= n) ? (mid / row) : n;
            }

            if (cnt < k) lo = mid;
            else hi = mid;
        }
       
        bw.write(hi + "");
        bw.flush();
        bw.close();

    }
}
