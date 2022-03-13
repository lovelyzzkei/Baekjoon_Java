import java.io.*;
import java.util.*;

public class pb_11401 {
    
    static int[][] cache;
    static final int INF = 1_000_000_007;

    static int bino(int n, int k) {
        if (k == 0 || n == k) return 1;
        if (cache[n][k] != 0) return cache[n][k];
        return cache[n][k] = (bino(n-1, k-1) % INF + bino(n-1, k) % INF) % INF;
        
    }
   

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        cache = new int[n+1][k+1];

        bw.write(String.format("%d", bino(n, k)));

        bw.flush();
        bw.close();

    }
}
