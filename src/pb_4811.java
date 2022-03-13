import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_4811 {

    static int n;
    static long[][] cache;

    static long eatDrug(int one, int half) {
        if (one == 0 && half == 0) return 1;
        if (one < 0 || half < 0) return 0;
        if (cache[one][half] != -1) return cache[one][half];
        return cache[one][half] = eatDrug(one, half-1) + eatDrug(one-1, half+1);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            cache = new long[n+1][n+1];
            for (long[] arr : cache) {
                Arrays.fill(arr, -1);
            }

            long ret = eatDrug(n, 0);
            bw.write(String.format("%d\n", ret));
        }
        bw.flush();
        bw.close();

    }
}
