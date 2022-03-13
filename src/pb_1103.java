import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1103 {

    static int n, m;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static char[][] board;
    static int[][] cache;
    static boolean[][] visited;
    
    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < m);
    }


    static int game(int y, int x) {
        if (!inRange(y, x) || board[y][x] == 'H') return 0;
        if (visited[y][x]) {
            System.out.println(-1);
            System.exit(0);
        }
        if (cache[y][x] != -1) return cache[y][x];
        
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i] * (board[y][x] - '0');
            int nx = x + dx[i] * (board[y][x] - '0');
            cache[y][x] = Math.max(cache[y][x], game(ny, nx) + 1);
        }
        visited[y][x] = false;

        return cache[y][x];
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        board = new char[n][m];
        cache = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            char[] row = br.readLine().toCharArray();
            System.arraycopy(row, 0, board[i], 0, m);
            Arrays.fill(cache[i], -1);
        }

        int ret = game(0, 0);

        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
