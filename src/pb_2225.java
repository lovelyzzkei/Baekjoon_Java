import java.io.*;
import java.util.*;

public class pb_2225 {

    static int n, k;
    static long[][] cache;
    static final int INF = 1_000_000_000;

    static long decomposeSum(int n, int numOfInt) {

        if (numOfInt == 0) return (n == 0) ? 1 : 0;
        if (cache[n][numOfInt] != 0) return cache[n][numOfInt];
        
        for (int i = 0; i <= n; i++) {
            cache[n][numOfInt] += (decomposeSum(n-i, numOfInt-1) % INF);
        }
        cache[n][numOfInt] %= INF;
        return cache[n][numOfInt];

    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        cache = new long[n+1][k+1];

        long ret = decomposeSum(n, k);

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
