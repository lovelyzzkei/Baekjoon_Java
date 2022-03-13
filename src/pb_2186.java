import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2186 {

    static int n, m, k;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][][] cache;
    static char[][] board;
    static String word;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < m);
    }

    static int dfs(int y, int x, int move) {
        if (move == word.length()) return 1;
        if (cache[y][x][move] != -1) return cache[y][x][move];

        cache[y][x][move] = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < 4; j++) {
                int ny = y + i * dy[j];
                int nx = x + i * dx[j];

                if (inRange(ny, nx) && board[ny][nx] == word.charAt(move)) {
                    cache[y][x][move] += dfs(ny, nx, move+1);
                }
            }
        }
        return cache[y][x][move];


    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);


        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        cache = new int[n][m][81];
        for (int[][] arr : cache) {
            for (int[] tmp : arr) {
                Arrays.fill(tmp, -1);
            }
        }

        int ret = 0;

        word = br.readLine();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    ret += dfs(i, j, 1);
                }
            }
        }
        
        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
