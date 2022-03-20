import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_6087 {

    static int w, h;
    static int startX, startY;
    static int destX, destY;
    static int[][][] cache;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static final int INF = 10001;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < h) && (0 <= x && x < w);
    }

    // dir: 이전 칸에서의 방향
    static int dp(int y, int x, int dir) {
        if (y == destY && x == destX) return cache[y][x][dir] = 0;
        if (cache[y][x][dir] != -1) return cache[y][x][dir];

        cache[y][x][dir] = INF;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (i != dir && i%2 == dir%2) continue;
            if (inRange(ny, nx) && cache[ny][nx][i] != 2*INF) {
                if (i == dir) {
                    cache[y][x][dir] = Math.min(cache[y][x][dir], dp(ny, nx, i));
                } else {
                    cache[y][x][dir] = Math.min(cache[y][x][dir], 1 + dp(ny, nx, i));
                }
            }
        }

        return cache[y][x][dir];

    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        w = Integer.parseInt(input[0]);
        h = Integer.parseInt(input[1]);

        startY = startX = -1;

        cache = new int[h][w][4];
        for (int i = 0; i < h; i++) {
            char[] inputArr = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                if (inputArr[j] == '.') Arrays.fill(cache[i][j], -1);
                if (inputArr[j] == '*') Arrays.fill(cache[i][j], 2*INF);
                if (inputArr[j] == 'C') {
                    Arrays.fill(cache[i][j], -1);
                    if (startY == -1) {
                        startY = i; startX = j;
                    }
                    else {
                        destY = i; destX = j;
                    }
                }
            }
        }

        int ret = INF;
        for (int dir = 0; dir < 4; dir++) {
            int ny = startY + dy[dir];
            int nx = startX + dx[dir];

            if (inRange(ny, nx)) {
                ret = Math.min(ret, dp(ny, nx, dir));
            }
        }

        for (int[][] arr : cache) {
            for (int[] tmp : arr) {
                System.out.print(Arrays.toString(tmp)+" ");
            }
            System.out.println();
        }

        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
