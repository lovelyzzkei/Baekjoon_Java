import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2011 {

    static final int INF = 1_000_000;

    static int n;
    static int[] cache, code;

    static int decode(int start) {

        if (start == n) return 1;
        if (start == n - 1) {
            return (code[start] == 0) ? 0 : 1;
        }
        if (cache[start] != -1) return cache[start];
        if (code[start] == 0) {
            return 0;
        }

        if (code[start]*10 + code[start+1] <= 26) {
            return cache[start] = (decode(start + 1) % INF + decode(start + 2) % INF) % INF;
        } else {
            return cache[start] = decode(start + 1) % INF;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        char[] input = br.readLine().toCharArray();
        n = input.length;
        code = new int[n];
        cache = new int[n];
        Arrays.fill(cache, -1);

        for (int i = 0; i < n; i++) {
            code[i] = input[i] - '0';
        }

        int ret = decode(0);
        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
