import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1240 {

    static int n, m, root, dest;
    static int[][] dist;
    static boolean[] visited;

    static int bfs() {
        Deque<Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[] {root, 0});
        visited[root] = true;

        int ret = 0;
        boolean isFind = false;

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int t = 0; t < qSize; t++) {
                Integer[] curr = q.pop();
                int currNode = curr[0];
                int d = curr[1];

                if (currNode == dest) {
                    isFind = true;
                    ret = d;
                    break;
                }
                
                for (int next = 1; next <= n; next++) {
                    if (!visited[next] && dist[currNode][next] != 0) {
                        visited[next] = true;
                        q.add(new Integer[] {next, d + dist[currNode][next]});
                    }
                }
            }

            if (isFind) break;
        }
        return ret;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        dist = new int[n+1][n+1];
        for (int i = 0; i < n-1; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);

            dist[u][v] = dist[v][u] = d;
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            root = Integer.parseInt(input[0]);
            dest = Integer.parseInt(input[1]);
            visited = new boolean[n+1];
            bw.write(bfs() + "\n");
        }
        
        bw.flush();
        bw.close();

    }
}
