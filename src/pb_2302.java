import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2302 {

    static int n;
    static int[] cache;

    static int calcSeat(int start, int end) {
        if (start >= end) return 1;
        if (cache[start] != -1) return cache[start];
        return cache[start] = calcSeat(start + 1, end) + calcSeat(start + 2, end);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());

        cache = new int[n+1];
        Arrays.fill(cache, -1);
        


        int m = Integer.parseInt(br.readLine());
        int ret = 1, start = 1;
        for (int i = 0; i < m; i++) {
            int vip = Integer.parseInt(br.readLine());
            ret *= calcSeat(start, vip-1);
            start = vip + 1;
        }
        ret *= calcSeat(start, n);

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
