import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_1941 {  

    static int n = 5;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static boolean[][] visited;
    static char[][] board;

    static boolean inRange(int y, int x) {
        return (0 <= y  && y < n) && (0 <= x && x < n);
    }


    static int dfs(int y, int x, int[] cache) {
        visited[y][x] = true;
        if (cache[0] + cache[1] == 7) {
            System.out.println(y + " " + " " + x + " " + Arrays.toString(cache));
            return (cache[0] > cache[1]) ? 1 : 0;
        }

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (inRange(ny, nx) && !visited[ny][nx]) {
                if (board[ny][nx] == 'S') {
                    cnt += dfs(ny, nx, new int[]{cache[0]+1, cache[1]});
                } else {
                    cnt += dfs(ny, nx, new int[]{cache[0], cache[1]+1});
                }
            }
        }
        return cnt;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        visited = new boolean[n][n];
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                for (boolean[] arr : visited) {
                    Arrays.fill(arr, false);
                }

                if (board[i][j] == 'S') {
                    ret += dfs(i, j, new int[]{1, 0});
                } else {
                    ret += dfs(i, j, new int[]{0, 1});
                }
            }
        }
        
        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
