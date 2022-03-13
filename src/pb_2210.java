import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2210 {  

    static int n = 5;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static String[][] board;
    static ArrayList<String> ans = new ArrayList<>();

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < n);
    }


    static void dfs(int y, int x, String num) {
        if (num.length() == 6) {
            ans.add(num);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (inRange(ny, nx)) {
                dfs(ny, nx, num+board[ny][nx]);
            }
        }
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        board = new String[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().split(" ");
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, board[i][j]);
            }
        }

        HashSet<String> ansToSet = new HashSet<>(ans);
        System.out.println(ansToSet.size());

        bw.flush();
        bw.close();

    }
}
