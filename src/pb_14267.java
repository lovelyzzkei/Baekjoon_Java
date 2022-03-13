import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_14267 {  

    static int n, m;
    static int[] parent, direct_praise, total_praise;
    static ArrayList<ArrayList<Integer>> tree;



    static void dfs(int curr) {
        total_praise[curr] += direct_praise[curr];
        for (Integer next : tree.get(curr)) {
            total_praise[next] += total_praise[curr];
            dfs(next);
        }
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        parent = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<Integer>());
        }

        for (int u = 2; u <= n; u++) {
            int v = parent[u];
            tree.get(v).add(u);
        }

        total_praise = new int[n+1];
        direct_praise = new int[n+1];
        

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int curr = Integer.parseInt(input[0]);
            int w = Integer.parseInt(input[1]);
            direct_praise[curr] += w;
        }

        dfs(1);

        for (int i = 1; i <= n; i++) {
            bw.write(total_praise[i] + " ");
        }

        bw.flush();
        bw.close();

    }
}
