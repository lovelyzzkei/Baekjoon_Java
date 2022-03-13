import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_7579 {

    static int n, m;
    static int[] memoryUsage, cost;
    static int[][] cache;

    static final int INF = -10_000_001;


    static int disabledApp(int app, int appCost) {
        if (app == n) {
            if (appCost == 0) return 0;
            else return INF;
        }
        if (cache[app][appCost] != -1) return cache[app][appCost];

        cache[app][appCost] = disabledApp(app + 1, appCost);
        if (appCost - cost[app] >= 0) {
            cache[app][appCost] = Math.max(cache[app][appCost], memoryUsage[app] + disabledApp(app + 1, appCost - cost[app]));
        }
        if (cache[app][appCost] <= INF) cache[app][appCost] = 0;
        return cache[app][appCost];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        memoryUsage = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cost = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int col = 0;
        for (int c : cost) {
            col += c;
        }
        cache = new int[n][col+1];
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }

        int ret = 10001;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= col; j++) {
                if (cost[i] <= j && disabledApp(i, j) >= m){
                    ret = Math.min(ret, j);
                }
            }
        }
    

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
