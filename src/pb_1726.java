import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1726 {    

    static int n, m;
    static int[][] map;
    static int[] dx = {0, 1, -1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};
    static int destY, destX, destDir;
    static final int INF = 10001;

    static boolean[][][] visited;
    
    static boolean inRange(int y, int x) {
        return (0 < y && y <= m) && (0 < x && x <= n);
    }

    static boolean degree180(int dir, int nextDir) {
        if (dir == 1) return (nextDir == 2) ? true : false;
        if (dir == 2) return (nextDir == 1) ? true : false;
        if (dir == 3) return (nextDir == 4) ? true : false;
        else return (nextDir == 3) ? true : false;
    }

    static boolean allZero(int y, int x, int dir, int k) {
        for (int i = 1; i <= k; i++) {
            int ny = y + i*dy[dir];
            int nx = x + i*dx[dir];
            if (map[ny][nx] != 0) return false;
        }
        return true;
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        n = Integer.parseInt(input[1]);

        map = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(input[j-1]);
            }
        }

        input = br.readLine().split(" ");
        int y = Integer.parseInt(input[0]);
        int x = Integer.parseInt(input[1]);
        int dir = Integer.parseInt(input[2]);

        input = br.readLine().split(" ");
        destY = Integer.parseInt(input[0]);
        destX = Integer.parseInt(input[1]);
        destDir = Integer.parseInt(input[2]);

        Integer[] start = new Integer[] {y, x, dir, 0};
        Deque<Integer[]> q = new ArrayDeque<>();
        q.add(start);

        visited = new boolean[m+1][n+1][5];
        visited[y][x][dir] = true;

        int ret = 0;
        boolean isArrived = false;

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int t = 0; t < qSize; t++) {

                Integer[] curr = q.pop();
                y = curr[0]; x = curr[1]; dir = curr[2]; int move = curr[3];

                if (y == destY && x == destX && dir == destDir) {
                    ret = move;
                    isArrived = true;
                    break;
                }

                for (int i = 1; i <= 4; i++) {
                    
                    // 방향이 같을 경우 명령 1: Go K를 수행
                    if (dir == i) {
                        for (int k = 1; k <= 3; k++) {
                            int ny = y + k * dy[i];
                            int nx = x + k * dx[i];
                            if (inRange(ny, nx) && !visited[ny][nx][i] && allZero(y, x, i, k)) {
                                visited[ny][nx][i] = true;
                                q.add(new Integer[] {ny, nx, i, move+1});
                            }
                        }
                    } 

                    // 그렇지 않을 경우 명령 2: Turn dir을 수행
                    else {
                        if (degree180(dir, i)) continue;
                        if (!visited[y][x][i]) {
                            visited[y][x][i] = true;
                            q.add(new Integer[] {y, x, i, move+1});
                        }
                    }
                }
            }

            if (isArrived) break;
            
        }
        

        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}

