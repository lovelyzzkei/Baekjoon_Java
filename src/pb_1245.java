import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1245 {

    static int n, m;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    static int[][] map;

    static boolean[][] visited;
    
    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < m);
    }

    // 현재 격자가 산봉우리에 해당하는지 확인
    static boolean isHill(int y, int x) {
        Deque<Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[] {y, x});

        while (!q.isEmpty()) {
            Integer[] curr = q.pop();
            int cy = curr[0];
            int cx = curr[1];


            for (int i = 0; i < 8; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (inRange(ny, nx)) {

                    // 주변 격자가 자신보다 크면 산봉우리가 아니다
                    if (map[ny][nx] > map[cy][cx]) {
                        return false;
                    } 
                    
                    // 자신과 높이가 동일하면 동일한 산봉우리
                    else if (map[ny][nx] == map[cy][cx] && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        q.add(new Integer[] {ny, nx});
                    }
                }
            }
        }

        return true;
    }


    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. N, M, 격자의 높이 입력 받음
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(arr, 0, map[i], 0, m);
        }

        // 2. 각 격자를 돌면서 해당 격자가 산봉우리에 해당하는지 확인.
        int cnt = 0;
        visited = new boolean[n][m];
        for (int i = 0; i< n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    cnt += (isHill(i, j) ? 1 : 0);
                }
            }
        }
        
        bw.write(cnt + "");
        bw.flush();
        bw.close();

    }
}
