import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_17136 {    

    static int[][] board;
    static boolean[][] visited;
    static int[] paper = {0, 0, 0, 0, 0, 0};

    static final int n = 10;
    static final int INF = 101;

    static int cnt = INF;
    static boolean isFull = false;
    static ArrayList<Integer[]> one = new ArrayList<>();

    

    static boolean inRange(int y, int x, int width) {
        return (0 <= y && y + width <= n) && (0 <= x && x + width <= n);
    }

    static boolean allOne(int y, int x, int width) {
        for (int i = y; i < y+width; i++) {
            for (int j = x; j < x+width; j++) {
                if (visited[i][j] || board[i][j] == 0) return false;
            }
        }
        return true;
    }

    static void coverOne(int y, int x, int width, boolean state) {
        for (int i = y; i < y+width; i++) {
            for (int j = x; j < x+width; j++) {
                visited[i][j] = state;
            }
        }
    }

    static void dfs(int idx) {
        if (idx == one.size()) {
            int sum = 0;
            for (int num : paper) sum += num; 
            cnt = Math.min(cnt, sum);
            return;
        }

        Integer[] curr = one.get(idx);
        int y = curr[0]; int x = curr[1];

        if (visited[y][x]) dfs(idx+1);

        for (int i = 5; i >= 1; i--) {
            if (inRange(y, x, i) && !visited[y][x] && allOne(y, x, i)) {
                if (paper[i] < 5) {
                    paper[i]++;
                    coverOne(y, x, i, true);
                    dfs(idx+1);
                    coverOne(y, x, i, false);
                    paper[i]--;

                }
            }
        }
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) one.add(new Integer[] {i, j});
            }
        }

        visited = new boolean[n][n];
        dfs(0);

        bw.write(((cnt == INF) ? -1 : cnt) + "");
        bw.flush();
        bw.close();

    }
}

