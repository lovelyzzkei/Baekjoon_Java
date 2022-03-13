import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_3584 {  

    static int n;
    static int start, end, idx;
    static boolean isStart, isEnd;
    static int[] depth, ancestor;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> tree;


    static int dfs(int curr) {
        visited[curr] = true;
        int cnt = 0;
        if (curr == start || curr == end) {
            cnt = 1;
        }
        for (Integer next : tree.get(curr)) {
            if (!visited[next]) {
                cnt += dfs(next);
                if (cnt == 2) {
                    ancestor[idx++] = curr;
                }
            }
        }
        
        return cnt;
    }


    static int findRoot(int curr) {
        visited[curr] = true;
        int cnt = 1;
        for (Integer next : tree.get(curr)) {
            if (visited[next]) {
                cnt += depth[next];
            } else {
                cnt += findRoot(next);
            }
        }
        return depth[curr] = cnt;
    }

    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        ArrayList<Integer> ans = new ArrayList<>();
        
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());

            tree = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= n; i++) {
                tree.add(new ArrayList<>());
            }
            visited = new boolean[n+1];

            for (int i = 0; i < n-1; i++) {
                String[] input = br.readLine().split(" ");
                int u = Integer.parseInt(input[0]);
                int v = Integer.parseInt(input[1]);

                tree.get(u).add(v);
            }

            String[] input = br.readLine().split(" ");
            start = Integer.parseInt(input[0]);
            end = Integer.parseInt(input[1]);

            isStart = isEnd = false;
            ancestor = new int[n+1];


            // root 찾기
            depth = new int[n+1];
            Arrays.fill(depth, -1);

            int root = 0;
            for (int i = 1; i <= n; i++) {
                if (findRoot(i) == n) {
                    root = i;
                    break;
                }
            }

            idx = 0;
            Arrays.fill(visited, false);
            dfs(root);
            ans.add(ancestor[0]);
        }

        for (Integer i : ans) {
            bw.write(i + "\n");
        }
        
        bw.flush();
        bw.close();

    }
}
