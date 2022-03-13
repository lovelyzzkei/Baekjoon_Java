import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_15732 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        int d = Integer.parseInt(input[2]);

        int[] start = new int[k];
        int[] end = new int[k];
        int[] interval = new int[k];

        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            start[i] = Integer.parseInt(input[0]);
            end[i] = Integer.parseInt(input[1]);
            interval[i] = Integer.parseInt(input[2]);

        }

        int lo = 0;
        int hi = n+1;

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            long sumOfDotori = 0;
            for (int i = 0; i < k; i++) {
                if (start[i] <= mid && mid <= end[i]) {
                    sumOfDotori += 1 + (mid - start[i]) / interval[i];
                } else if (mid > end[i]) {
                    sumOfDotori += 1 + (end[i] - start[i]) / interval[i];
                }

            }

            if (sumOfDotori < d) lo = mid;
            else hi = mid;
        }

        bw.write(hi + "");
        bw.flush();
        bw.close();

    }
}
