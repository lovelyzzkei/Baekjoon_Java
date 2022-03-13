import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2580 {    

    static final int n = 9;
    static int[][] board;
    static boolean[][] visited;

    static boolean[][] row;
    static boolean[][] column;
    static boolean[][] square;
    static boolean isFind = false;
    static ArrayList<Integer[]> blank;

    static void dfs(int idx) {
        if (isFind) return;
        if (idx == blank.size()) {
            for (int[] arr : board) {
                for (int tmp : arr) {
                    System.out.print(tmp + " ");
                }
                System.out.println();
            }
            isFind = true;
            return;
        }
        Integer[] curr = blank.get(idx);
        int y = curr[0]; int x = curr[1];

        for (int i = 1; i <= n; i++) {
            if (!row[y][i] && !column[x][i] && !square[3*(y/3)+x/3][i]) {
                row[y][i] = column[x][i] = square[3*(y/3)+x/3][i] = true;
                board[y][x] = i;
                visited[y][x] = true;

                dfs(idx+1);
                row[y][i] = column[x][i] = square[3*(y/3)+x/3][i] = false;
                board[y][x] = 0;
                visited[y][x] = false;

            }
        }
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        row = new boolean[n][n+1];
        column = new boolean[n][n+1];
        square = new boolean[n][n+1];
        visited = new boolean[n][n+1];
        blank = new ArrayList<>();

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {

                // 해당 칸이 0이 아니면 
                // 현재 행에서 해당 수는 true; 현재 열에서 해당 수도 true;
                int num = board[i][j];
                if (num != 0) {
                    visited[i][j] = true;
                    row[i][num] = true;
                    column[j][num] = true;
                    square[3*(i/3)+j/3][num] = true;
                } else {
                    blank.add(new Integer[]{i, j});
                }
            }
        }
        dfs(0);
        

        bw.flush();
        bw.close();

    }
}

