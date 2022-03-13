import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2533 {

    static final int INF = 1_000_000_000;

    static int n;
    static ArrayList<ArrayList<Integer>> adj, child;
    static boolean[] visited;
    static int[][] cache, picked;


    static void dfs(int curr) {
        visited[curr] = true;
        for (int next : adj.get(curr)) {
            if (!visited[next]) {
                child.get(curr).add(next);
                dfs(next);
            }
        }
    }


    static int sns(int curr, int pre) {
        if (cache[curr][pre] != -1) return cache[curr][pre];

        cache[curr][pre] = INF;
        if (pre == 0) {
            cache[curr][pre] = 1;
            for (int next : child.get(curr)) {
                cache[curr][pre] += sns(next, 1);
            }
            picked[curr][pre] = 1;
        } else {
            int currEA = 1; 
            int currNotEA = 0;
            for (int next : child.get(curr)) {
                currEA += sns(next, 1);
                currNotEA += sns(next, 0);
            }
            cache[curr][pre] = Math.min(currEA, currNotEA);
            picked[curr][pre] = (currEA < currNotEA) ? currEA : currNotEA;
        }
        return cache[curr][pre];
    }


    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());

        adj = new ArrayList<ArrayList<Integer>>();
        child = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<Integer>());
            child.add(new ArrayList<Integer>());
        }

        for (int t = 0; t < n-1; t++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        visited = new boolean[n+1];
        dfs(1);

        cache = new int[n+1][2];
        picked = new int[n+1][2];
        for (int[] arr : cache) {
            Arrays.fill(arr, -1);
        }

        int ret = sns(1, 1);

        for (int[] arr : picked) {
            System.out.println(Arrays.toString(arr));
        }


        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
