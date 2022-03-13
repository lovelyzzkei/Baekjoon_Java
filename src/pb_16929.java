import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_16929 {  

    static int n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static boolean isCycle = false;
    static boolean[][] visited, finished;
    static char[][] board;

    static boolean inRange(int y, int x) {
        return (0 <= y  && y < n) && (0 <= x && x < m);
    }


    static void dfs(int y, int x, int py, int px) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            // 보드 안에 있고 색이 같은 경우
            if (inRange(ny, nx) && board[ny][nx] == board[y][x] && (ny != py || nx != px)) {
                if (visited[ny][nx] && !finished[ny][nx]) {
                    isCycle = true;
                    return;
                } else {
                    dfs(ny, nx, y, x);
                }
            }
        }
        finished[y][x] = true;
        return;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        visited = new boolean[n][m];
        finished = new boolean[n][m];
        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, -1, -1);
                }
            }
        }
        
        bw.write((isCycle) ? "Yes" : "No" + "");
        bw.flush();
        bw.close();

    }
}
