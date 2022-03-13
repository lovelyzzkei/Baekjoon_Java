import java.io.*;
import java.util.*;

public class pb_1915 {

    static int n, m;
    static int[][] board, cache;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < m);
    }

    static int maxSquare(int y, int x) {
        if (!inRange(y, x)) return 0;
        if (cache[y][x] != -1) return cache[y][x];

        cache[y][x] = board[y][x];

        // 왼쪽 위 꼭짓점이 (y+1,x+1)인 정사각형이 있고, 
        // 자신의 y와 x가 1이면 (y,x)인 정사각형 생성 가능
        if (inRange(y+1, x+1) && cache[y+1][x+1] != -1) {

            int side = cache[y+1][x+1];
            int ableSide = side;

            for (int i = 1; i <= side; i++) {
                if (board[y+i][x] == 0 || board[y][x+i] == 0) {
                    ableSide = i-1;
                    break;
                }
            }
            cache[y][x] += ableSide;
            
        }

        return cache[y][x];
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 1. 입력 받기
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        board = new int[n][m];
        cache = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = (row[j] - '0');
            }
            Arrays.fill(cache[i], -1);
        }

        // 2. 가장 큰 정사각형 넓이 계산하기
        // 뒤부터 반복문 계산
        int ret = 0;
        for (int i = n-1; i > -1; i--) {
            for (int j = m-1; j > -1; j--) {
                if (board[i][j] != 0) {
                    ret = Math.max(ret, maxSquare(i, j));
                }
            }
        }


        bw.write(String.format("%d", (int) Math.pow(ret, 2)));
        bw.flush();
        bw.close();

    }
}
