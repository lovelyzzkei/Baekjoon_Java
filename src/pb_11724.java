import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_11724 {    

    static int n, m;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;

    static void dfs(int curr) {
        visited[curr] = true;
        for (int next : graph.get(curr)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        int cnt = 0;
        visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i);
                cnt++;
            }
        }
        
        bw.write(cnt + "");       
        bw.flush();
        bw.close();

    }
}
