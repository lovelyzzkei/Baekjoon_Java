import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_3109 {

    static int r, c;
    static int[] dx = {1, 1, 1};
    static int[] dy = {1, 0, -1};
    static int[][][] cache;
    static boolean[][] visited;
    static final int INF = 1_000_000_000;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < r) && (0 <= x && x < c);
    }

    static int dfs(int y, int x, int dir) {
        visited[y][x] = true;
        return 0;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);         
        c = Integer.parseInt(input[1]);         

        cache = new int[r][c][3];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                if (input[j].equals(".")) {
                    Arrays.fill(cache[i][j], -1);
                } else {
                    Arrays.fill(cache[i][j], INF);
                }
            }
        }


    
        int ret = 0;
        for (int i = 0; i < r; i++) {
            for (int dir = 0; dir < 3; dir++) {
                ret += dfs(i, 0, dir);
            }
        }

        

        bw.flush();
        bw.close();

    }
}
