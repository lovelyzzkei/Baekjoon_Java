import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_9507 {

    static int n;
    static long[] cache;

    static long koong(int n) {
        if (n < 2) return cache[n] = 1;
        if (n == 2) return cache[n] = 2;
        if (n == 3) return cache[n] = 4;
        if (cache[n] != -1) return cache[n];
        return cache[n] = koong(n-1) + koong(n-2) + koong(n-3) + koong(n-4);
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        cache = new long[68];
        Arrays.fill(cache, -1);
        long ret = koong(67);
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            bw.write(String.format("%d\n", cache[n]));
        }

        bw.flush();
        bw.close();

    }
}
