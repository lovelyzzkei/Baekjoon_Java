import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_14503 {

    static int n, m;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] map;
    static boolean[][] visited;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < m);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        int y = Integer.parseInt(input[0]);
        int x = Integer.parseInt(input[1]);
        int d = Integer.parseInt(input[2]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(arr, 0, map[i], 0, m);
        }

        visited = new boolean[n][m];
        visited[y][x] = true;

        int ret = 1;
        boolean isFinished = false;

        while (true) {
            if (isFinished) break;


            int numOf2a = 0;
            for (int i = 0; i < 4; i++) {
                int nextDir = (d == 0) ? 3 : d - 1;
                int ny = y + dy[nextDir];
                int nx = x + dx[nextDir];
                if (inRange(ny, nx) && !visited[ny][nx] && map[ny][nx] == 0) {
                    y = ny; x = nx; d = nextDir;
                    visited[ny][nx] = true;
                    ret++;
                    break;
                } else {
                    d = nextDir;
                    numOf2a++;
                }
            }

            if (numOf2a == 4) {
                int nextDir = 2 - d;
                if (d == 1 || d == 3) {
                    nextDir = 4 - d;
                }
                int ny = y + dy[nextDir];
                int nx = x + dx[nextDir];
                if (inRange(ny, nx) && map[ny][nx] == 1) {
                    isFinished = true;
                } else {
                    y = ny; x = nx;
                }
            }
        }
        
        bw.write(ret +"");
        bw.flush();
        bw.close();

    }
}
