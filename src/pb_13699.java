import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_13699 {

    static int n;
    static long[] cache;
    
    static long t(int n) {
        if (n == 0) return 1;
        if (cache[n] != -1) return cache[n];
        
        cache[n] = 0;
        for (int i = 0; i <= n-1; i++) {
            cache[n] += t(i) * t(n-1-i);
        }
        return cache[n];
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        cache = new long[n+1];
        Arrays.fill(cache, -1);

        long ret = t(n);

        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
