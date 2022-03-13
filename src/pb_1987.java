import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_1987 {

    static int r, c;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static char[][] board;
    static boolean[] alphabet;
    static boolean[][] visited;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < r) && (0 <= x && x < c);
    }

    static int dfs(int y, int x) {
        visited[y][x] = true;
        char curr = board[y][x];
        alphabet[curr-'A'] = true;

        int ret = 1;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (inRange(ny, nx) && !visited[ny][nx]) {
                int cand = 1;
                char next = board[ny][nx];
                if (!alphabet[next-'A']) {
                    cand += dfs(ny, nx);
                }
                ret = Math.max(ret, cand);
            }
        }
        visited[y][x] = false;
        alphabet[curr-'A'] = false;
        return ret;
    }
    
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            System.arraycopy(br.readLine().toCharArray(), 0, board[i], 0, c);
        }

        visited = new boolean[r][c];
        alphabet = new boolean[26];

        int ret = dfs(0, 0);
        
        bw.write(ret+"");
        bw.flush();
        bw.close();

    }
}
