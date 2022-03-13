import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2169 {

    static int[] dy = {0, 1, 0};
    static int[] dx = {-1, 0, 1};

    static int n, m;
    static int[][] mars;
    static int[][][] cache;

    static final int INF = -200001;

    static boolean inRange(int y, int x) {
        return ((0 < y && y <= n) && (0 < x && x <= m));
    }

    // (y, x)에서 이전 방향이 dir일때 (n, m) 까지의 최댓값
    static int exploreMars(int y, int x, int dir) {
        if (y == n && x == m) return cache[y][x][dir] = mars[n][m];
        if (!inRange(y, x)) return INF;
        if (cache[y][x][dir] != INF) return cache[y][x][dir];

        switch (dir) {
            case 0:
                cache[y][x][dir] = Math.max(exploreMars(y, x+1, 0),
                                            exploreMars(y+1, x, 1)) + mars[y][x];
                break;
            case 1:
                cache[y][x][dir] = Math.max(exploreMars(y, x+1, 0),
                                    Math.max(exploreMars(y+1, x, 1),
                                            exploreMars(y, x-1, 2))) + mars[y][x];
                break;
            case 2:
                cache[y][x][dir] = Math.max(exploreMars(y+1, x, 1),
                                            exploreMars(y, x-1, 2)) + mars[y][x];
                break;
        }



        // for (int[][] arr : cache) {
        //     for (int[] tmp : arr) {
        //         System.out.print(Arrays.toString(tmp) + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        return cache[y][x][dir];
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        mars = new int[n+1][m+1];
        cache = new int[n+1][m+1][3];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                cache[i][j] = new int[] {INF, INF, INF};
            }
        }

        for (int i = 1; i <= n; i++) {
            int[] tmp = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(tmp, 0, mars[i], 1, m);
        }


        int ret = exploreMars(1, 1, 0);
       
        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
