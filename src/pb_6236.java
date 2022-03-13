import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_6236 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(br.readLine());
        }

        int lo = 0;
        int hi = 1_000_000_000;

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            int budget = 0; 
            int cnt = 0;
            boolean isImpossible = false;
            for (int i = 0; i < n; i++) {
                if (cost[i] > budget) {
                    cnt++;
                    budget = mid - cost[i];
                    if (budget < 0) {
                        isImpossible = true;
                        break;
                    }
                } else {
                    budget -= cost[i];
                }
            }

            if (cnt > m || isImpossible) lo = mid;
            else hi = mid;
        }

        bw.write(hi + "");
        bw.flush();
        bw.close();

    }
}
