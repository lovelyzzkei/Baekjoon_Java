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
                if (inputArr[j] == '*') Arrays.fill(cache[i][j], INF);
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

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b)->(a[0]-b[0]));
        for (int dir = 0; dir < 4; dir++) {
            pq.add(new Integer[] {0, startY, startX, dir});
        }

        while (!pq.isEmpty()) {

            Integer[] curr = pq.poll();
            int numOfMirror = curr[0];
            int y = curr[1];
            int x = curr[2];
            int dir = curr[3];

            if (y == destY && x == destX) {
                ret = numOfMirror;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (i != dir && i%2 == dir%2) continue;
                if (inRange(ny, nx) && cache[ny][nx][i] != INF) {
                    cache[y][x][dir] = INF; // 방문처리
                    if (i == dir) {
                        pq.add(new Integer[] {numOfMirror, ny, nx, i});
                    } else {
                        pq.add(new Integer[] {numOfMirror+1, ny, nx, i});
                    }
                }
            }
            
        }

        bw.write(ret + "");
        bw.flush();
        bw.close();
    }
}
