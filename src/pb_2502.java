import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2502 {

    static int d, k;
    static int[] cache;

    static int fibonachi(int n) {
        if (n == 0) return cache[n] = 0;
        if (n == 1) return cache[n] = 1;
        if (cache[n] != -1) return cache[n];
        return cache[n] = fibonachi(n-1) + fibonachi(n-2);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        d = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        cache = new int[31];
        Arrays.fill(cache, -1);
        int ret = fibonachi(30);
    
        for (int a = 1; a <= 100000; a++) {
            long mul = a * cache[d-2];
            int tmp = k - a * cache[d-2];
            if ((mul > 0 && mul <= 100000) && tmp >= 0 && tmp % cache[d-1] == 0) {
                System.out.println(a);
                System.out.println(tmp / cache[d-1]);
                return;
            }
        }
        bw.flush();
        bw.close();

    }
}
