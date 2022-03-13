import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2512 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] budget = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(budget);

        int m = Integer.parseInt(br.readLine());        

        int lo = 0;
        int hi = budget[budget.length-1]+1;

        while (lo+1 < hi) {
            int mid = (lo + hi) / 2;

            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (budget[i] > mid) sum += mid;
                else sum += budget[i];
            }

            if (sum > m) hi = mid;
            else lo = mid;
        }
        bw.write(lo + "");
        
        bw.flush();
        bw.close();

    }
}
