import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_17070 {

    static int n;
    static int[][] house;
    static int[][][] cache;
    static int[] dy = {0, 1, 1};
    static int[] dx = {1, 1, 0};

    static boolean inRange(int y, int x) {
        if ((0 <= y && y < n) && (0 <= x && x < n)) {
            if (house[y][x] != 1) {
                return true;
            }
        }
        return false;
    }

    static int[] nextDir(int n) {
        int[] nextDir;

        switch (n) {
            case 0:  // 가로
                nextDir = new int[] {0, 1};
                break;
            case 1: // 대각
                nextDir = new int[] {0, 1, 2};
                break;
            case 2: // 세로
                nextDir = new int[] {1, 2};
                break;
            default:
                nextDir = new int[3];
                break;
        }
        return nextDir;
    }

    static int movePipe(int y, int x, int dir) {
        if (!inRange(y, x)) return 0;
        if (y == n-1 && x == n-1) return 1;

        int[] nextDir = nextDir(dir);
        int num = 0;
        boolean isAllVisited = true;
        for (int i : nextDir) {
            if (cache[y][x][i] == -1){
                isAllVisited = false;
                break;
            } else {
                num += cache[y][x][i];
            }
        }
        
        if (isAllVisited && num != 0) return num;

        for (int i : nextDir) {
            cache[y][x][i] = 0;
            int ny = y + dy[i], nx = x + dx[i];
            if (inRange(ny, x) && inRange(y, nx) && inRange(ny, nx)) {
                cache[y][x][i] += movePipe(ny, nx, i);
            }
        }

        num = 0;
        for (int i : nextDir) {
            num += (cache[y][x][i] != -1) ? cache[y][x][i] : 0;
        }
        return num;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        house = new int[n][n];
        cache = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(input, 0, house[i], 0, n);
            for (int j = 0; j < n; j++) {
                cache[i][j] = new int[]{-1, -1, -1};
            }

        }

        int ret = movePipe(0, 1, 0);

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
