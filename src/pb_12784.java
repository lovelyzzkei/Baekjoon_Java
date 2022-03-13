import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_12784 {

    static int n, m;
    static int[] cache;
    static int[][] tree;
    static boolean[] visited;
    static final int INF = 1_000_000;


    static int dfs(int curr) {
        visited[curr] = true;
        if (cache[curr] != -1) return cache[curr];

        cache[curr] = 0;
        for (int next = 1; next <= n; next++) {
            if (tree[curr][next] != 0 && !visited[next]) {
                cache[curr] += Math.min(tree[curr][next], dfs(next));
            }
        }

        // leaf 노드의 다이너마이트는 INF로 상정
        if (cache[curr] == 0) cache[curr] = INF;    

        return cache[curr];
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);

            tree = new int[n+1][n+1];
            for (int i = 0; i < m; i++) {
                input = br.readLine().split(" ");
                int u = Integer.parseInt(input[0]);
                int v = Integer.parseInt(input[1]);
                int d = Integer.parseInt(input[2]);

                tree[u][v] = tree[v][u] = d;
            }


            visited = new boolean[n+1];
            cache = new int[n+1];
            Arrays.fill(cache, -1);
            int ret = dfs(1);
            bw.write(((ret != INF) ? ret : 0) + "\n");

        }
        
        
        bw.flush();
        bw.close();

    }
}
