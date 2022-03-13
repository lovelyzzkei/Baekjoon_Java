import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_10164 {

    static int n, m, s;
    static int[][] board;

    static int route(int sy, int sx, int ey, int ex) {
        board = new int[ey+1][ex+1];
        for (int i = sy; i <= ey; i++) {
            for (int j = sx; j <= ex; j++) {
                if (i == 0 || j == 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = board[i-1][j] + board[i][j-1];
                }
            }
        }
        return board[ey][ex];
    }

    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        s = Integer.parseInt(input[2]);
        
        int midy = 0, midx = 0, cnt = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (cnt == s) {
                    midy = i - 1; midx = j - 1;
                }
                cnt++;
            }
        }

        int ret = route(0, 0, midy, midx) * route(0, 0, (n-1)-midy, (m-1)-midx);

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
