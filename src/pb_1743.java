import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_1743 {

    static int n, m;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static boolean[][] visited, condo;

    static boolean inRange(int y, int x) {
        return (0 < y && y <= n) && (0 < x && x <= m);
    }

    static int dfs(int y, int x) {

        visited[y][x] = true;

        int size = 1;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (inRange(ny, nx) && !visited[ny][nx] && condo[ny][nx]) {
                size += dfs(ny, nx);
            }
        }
        return size;
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        visited = new boolean[n+1][m+1];
        condo = new boolean[n+1][m+1];

        for (boolean [] arr : visited) {
            Arrays.fill(arr, false);
        }

        for (boolean [] arr : condo) {
            Arrays.fill(arr, false);
        }

        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            condo[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = true;
        }


        int ret = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (!visited[i][j] && condo[i][j]) {
                    ret = Math.max(ret, dfs(i, j));
                }
            }
        }


        bw.write(ret+"");
        bw.flush();
        bw.close();

    }
}
