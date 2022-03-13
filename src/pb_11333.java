import java.io.*;
import java.util.*;

public class pb_11333 {

    static long[] cache;
    static final int INF = 1_000_000_007;

    static long tiling(int n) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        if (cache[n] != -1) return cache[n];
        cache[n] = (3 * tiling(n-3)) % INF;
        for (int i = 6; i <= n; i+=3) {
            
            cache[n] += (2 * (i / 3) * tiling(n-i)) % INF;
        }
        return cache[n] % INF;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            cache = new long[n+1];
            Arrays.fill(cache , -1);
            bw.write(String.format("%d\n", tiling(n)));
        }

        
        bw.flush();
        bw.close();

    }
}
