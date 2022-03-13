import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2688 {

    static int n;
    static long[][] cache;
    static final int INF = 1_000_000_003;

    static long notDecreaseNum(int start, int len) {
        if (len == 1) return 1;
        if (cache[start][len] != -1) return cache[start][len];
        
        cache[start][len] = 0;
        for (int next = start; next <= 9; next++) {
            cache[start][len] += notDecreaseNum(next, len-1);
        }
        return cache[start][len];
    }   


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());

        cache = new long[10][65];
        for (long[] arr : cache) {
            Arrays.fill(arr, -1);
        }

        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            long ret = 0;
            for (int start = 0; start <= 9; start++) {
                ret += notDecreaseNum(start, n);
            }
            bw.write(ret + "\n");
        }

        bw.flush();
        bw.close();

    }
}
