import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1937 {

    static int n;
    static int[][] bamboo;
    static int[][] cache;
    static final int INF = 1_000_001;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < n);
    }

    static boolean isNotMaximum(int y, int x) {
        int now = bamboo[y][x];
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];
            if (inRange(ny, nx) && now < bamboo[ny][nx]){
                return true;
            }
        }
        return false;
    }

    static int eatBamboo(int y, int x) {
        if (!inRange(y, x)) return 0;
        if (!isNotMaximum(y, x)) return 1;

        if (cache[y][x] != -1) return cache[y][x];

        cache[y][x] = 1;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];
            if (inRange(ny, nx) && bamboo[ny][nx] > bamboo[y][x]) {
                cache[y][x] = Math.max(cache[y][x], eatBamboo(ny, nx)+1);
            }
        }

        return cache[y][x];
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        bamboo = new int[n][n];
        cache = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1);
            Arrays.fill(bamboo[i], INF);
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(input, 0, bamboo[i], 0, n);
        }

        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ret = Math.max(ret, eatBamboo(i, j));
            }
        }
        
        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
