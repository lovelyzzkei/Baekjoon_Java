import java.io.*;
import java.util.*;

public class pb_15990 {

    static int n;
    static long[][] cache;
    static final int INF = 1_000_000_009;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int t = Integer.parseInt(br.readLine());
        cache = new long[100001][4];
        
        cache[1][1] = 1; cache[1][2] = 0; cache[1][3] = 0;
        cache[2][1] = 0; cache[2][2] = 1; cache[2][3] = 0;
        cache[3][1] = 1; cache[3][2] = 1; cache[3][3] = 1;
        

        for (int i = 4; i <= 100000; i++) {
            cache[i][1] = (cache[i-1][2] % INF + cache[i-1][3] % INF) % INF;
            cache[i][2] = (cache[i-2][1] % INF + cache[i-2][3] % INF) % INF;
            cache[i][3] = (cache[i-3][1] % INF + cache[i-3][2] % INF) % INF;
        }
        for (int j = 0; j < t; j++) {
            n = Integer.parseInt(br.readLine());
            
            bw.write(String.format("%d\n", (cache[n][1] + cache[n][2] + cache[n][3]) % INF));
        }
        bw.flush();
        bw.close();

    }
}
