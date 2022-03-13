import java.io.*;
import java.util.*;

public class pb_15988 {

    static int n;
    static long[] cache;
    static final int INF = 1_000_000_009;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int t = Integer.parseInt(br.readLine());
        cache = new long[1_000_001];
        Arrays.fill(cache, -1);
        cache[1] = 1; cache[2] = 2; cache[3] = 4;
        for (int i = 4; i <= 1_000_000; i++) {
            cache[i] = (cache[i-1] % INF + cache[i-2] % INF + cache[i-3] % INF) % INF;
        }
        for (int j = 0; j < t; j++) {
            n = Integer.parseInt(br.readLine());
            
            bw.write(String.format("%d\n", cache[n]));
        }
        bw.flush();
        bw.close();

    }
}
