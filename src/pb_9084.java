import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_9084 {

    static int n, m;
    static int[] coin;
    static int[][] cache;

    static int makePrice(int now, int beforeIdx) {
        if (now == 0) return 1;
        if (cache[now][beforeIdx] != -1) return cache[now][beforeIdx];

        cache[now][beforeIdx] = 0;
        for (int i = 0; i < coin.length; i++) {
            if (now - coin[i] >= 0 && coin[i] <= coin[beforeIdx]) {
                cache[now][beforeIdx] += makePrice(now - coin[i], i);
            }
        }
        

        return cache[now][beforeIdx];
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            coin = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            m = Integer.parseInt(br.readLine());

            cache = new int[m+1][coin.length];
            for (int[] arr : cache) {
                Arrays.fill(arr, -1);
            }

            bw.write(String.format("%d\n", makePrice(m, coin.length-1)));
        }
        bw.flush();
        bw.close();

    }
}
