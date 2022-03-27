import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_15681 {

    static int n, r, q;
    static int[] cache;
    static ArrayList<ArrayList<Integer>> graph, tree;
    static boolean[] visited;

    static void makeTree(int curr) {
        visited[curr] = true;
        for (int next : graph.get(curr)) {
            if (!visited[next]) {
                tree.get(curr).add(next);
                makeTree(next);
            }
        }
    }

    static int dfs(int curr) {
        if (cache[curr] != -1) return cache[curr];

        cache[curr] = 1;
        for (int next : tree.get(curr)) {
            cache[curr] += dfs(next);
        }
        return cache[curr];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        r = Integer.parseInt(input[1]);
        q = Integer.parseInt(input[2]);

        graph = new ArrayList<>();
        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n-1; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visited = new boolean[n+1];
        makeTree(r);

        cache = new int[n+1];
        Arrays.fill(cache, -1);
         
        for (int i = 0; i < q; i++) {
            int node = Integer.parseInt(br.readLine());
            bw.write(dfs(node) + "\n");
        }

        bw.flush();
        bw.close();

    }
}
