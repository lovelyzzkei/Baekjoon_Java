import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_3973 {

    static int c, n;
    static int[] cache;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;
    static final int INF = 100001;


    static int dfs(int curr, int dist) {
        visited[curr] = true;
        if (cache[curr] != -1) return cache[curr];
        
        for (int next : graph.get(curr)) {
            if (!visited[next]) {
                cache[curr] = Math.max(cache[curr], Math.max(dist, 1 + dfs(next, dist + 1)));
            }
        }

        return (cache[curr] != -1) ? cache[curr] : 0;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        c = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < c; t++) {
           n = Integer.parseInt(br.readLine());

           graph = new ArrayList<>();
           for (int i = 0; i < n; i++) {
               graph.add(new ArrayList<>());
           }

           for (int i = 0; i < n-1; i++) {
               String[] input = br.readLine().split(" ");
               int a = Integer.parseInt(input[0]);
               int b = Integer.parseInt(input[1]);

               graph.get(a).add(b);
               graph.get(b).add(a);
           }

           cache = new int[n];
           Arrays.fill(cache, -1);

           int ret = INF;
           for (int i = 0; i < n; i++) {
                visited = new boolean[n];
                ret = Math.min(ret, dfs(i, 0));
           }    

           System.out.println(Arrays.toString(cache));
           bw.write(ret + "\n");
        }

        bw.flush();
        bw.close();

    }
}
