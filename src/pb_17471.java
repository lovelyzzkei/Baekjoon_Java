import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_17471 {  

    static int n;
    static int[] value;
    static boolean[] visited, visitedAnotherTree;
    static boolean[][] area;
    static final int INF = 1_000_000;

    static int dfs(int curr) {
        visitedAnotherTree[curr] = true;
        int size = 1;
        for (int next = 1; next <= n; next++) {
            if (!visitedAnotherTree[next] && area[curr][next]) {
                size += dfs(next);
            }
        }
        return size;
    }

    static int splitArea(int curr, int size) {
        visited[curr] = true;

        int anotherTreeSize = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                System.arraycopy(visited, 0, visitedAnotherTree, 0, n+1);
                anotherTreeSize = dfs(i);
                break;
            }
        }

        int ret = INF;
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        if (size + anotherTreeSize == n) {
            int treeValue = 0;
            int anotherTreeValue = 0;
            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    treeValue += value[i];
                    a.add(i);
                } else {
                    anotherTreeValue += value[i];
                    b.add(i);
                }
            }
            System.out.println(a.toString());
            System.out.println(b.toString());
            ret = Math.min(ret, Math.abs(treeValue-anotherTreeValue));
        }


        for (int next = 1; next <= n; next++) {
            if (!visited[next] && area[curr][next]) {
                ret = Math.min(ret, splitArea(next, size+1));
            }
        }
        visited[curr] = false;  
        return ret;
    }




    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        value = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        area = new boolean[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(input[0]); j++) {
                int v = Integer.parseInt(input[j]);
                area[i][v] = true;
            }
        }

        visited = new boolean[n+1];
        visitedAnotherTree = new boolean[n+1];
        int ret = splitArea(1, 1);

        bw.write(((ret == INF) ? -1 : ret) + " ");
        bw.flush();
        bw.close();

    }
}
