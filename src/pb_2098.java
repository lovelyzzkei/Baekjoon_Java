import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2098 {

    static int[][] dist, cache;
    static int n, start;
    static final int INF = 10_000_000;

    static int shortestPath(int here, int visited) {
        if (visited == (1 << n) - 1) return (dist[here][start] == 0) ? INF : dist[here][start];
        if (cache[here][visited] != -1) return cache[here][visited];
        cache[here][visited] = INF;
        for (int next = 0; next < n; next++) {
            if ((visited & (1 << next)) >= 1 || (dist[here][next] == 0)) continue;
            int cand = dist[here][next] + shortestPath(next, visited + (1 << next));
            cache[here][visited] = Math.min(cache[here][visited], cand);
        
        }

        return cache[here][visited];
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        dist = new int[n][n];
        for (int[] arr : dist) {
            int[] inputCost = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(inputCost, 0, arr, 0, n);
        }

        cache = new int[n][1 << n];

        int ret = INF;
        for (int i = 0; i < n; i++) {
            for (int[] arr : cache) {
                Arrays.fill(arr, -1);
            }
            start = i;
            ret = Math.min(ret, shortestPath(i, (1 << i)));
        }


        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
