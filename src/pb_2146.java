import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2146 {    

    static int n;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] map;
    static boolean[][] visited;
    static final int INF = 10001;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < n);
    }

    static int divideIsland(int y, int x) {
        Deque<Integer[]> q = new ArrayDeque<>();
        Deque<Integer[]> qLeaf = new ArrayDeque<>();

        q.add(new Integer[] {y, x});
        visited[y][x] = true;

        // 섬의 리프노드들 탐색
        while (!q.isEmpty()) {
            Integer[] curr = q.pop();
            int cy = curr[0]; int cx = curr[1];
            boolean isLeaf = true;

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if (inRange(ny, nx) && !visited[ny][nx] && map[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    isLeaf = false;
                    q.add(new Integer[] {ny, nx});
                }
            }
            if (isLeaf) {
                qLeaf.add(curr);
            }
        }


        // 각 섬의 리프 노드들에서부터 다른 섬까지의 탐색
        int length = 0;
        boolean isArrived = false;

        while (!qLeaf.isEmpty()) {

            int qSize = qLeaf.size();
            for (int t = 0; t < qSize; t++) {
                Integer[] curr = qLeaf.pop();
                int cy = curr[0]; int cx = curr[1];

                if (length != 0 && map[cy][cx] == 1) {
                    length--;
                    visited[cy][cx] = false;
                    isArrived = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = cy + dy[i];
                    int nx = cx + dx[i];

                    if (inRange(ny, nx) && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        qLeaf.add(new Integer[] {ny, nx});
                    }
                }
            }

            if (isArrived) break;
            length++;
        }
        return length;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 각 섬 구분
        int ret = INF;
        visited = new boolean[n][n];

        // 각 섬의 리프 노드에서 다른 섬까지의 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    
                    for (boolean[] arr : visited) {
                        Arrays.fill(arr, false);
                        
                    }

                    ret = Math.min(ret, divideIsland(i, j));
                }
            }
        }

        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}

