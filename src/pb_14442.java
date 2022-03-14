import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_14442 {

    static int n, m, k;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] map;
    static boolean[][][] visited;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < m);
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]); 
        m = Integer.parseInt(input[1]); 
        k = Integer.parseInt(input[2]); 
        
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] inputArr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = inputArr[j] - '0';
            }
        }

        visited = new boolean[n][m][k+1];
        visited[0][0][0] = true;

        Deque<Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[] {0, 0, 0});

        int dist = 1;
        boolean isArrived = false;

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int t = 0; t < qSize; t++) {
                Integer[] curr = q.pop();
                int y = curr[0]; int x = curr[1]; int numOfWall = curr[2];

                if (y == n-1 && x == m-1) {
                    isArrived = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (inRange(ny, nx)) {
                        int nWall = numOfWall + (map[ny][nx] == 0 ? 0 : 1);
                        if (nWall <= k && !visited[ny][nx][nWall]) {
                            visited[ny][nx][nWall] = true;
                            q.add(new Integer[] {ny, nx, nWall});
                        }
                    }
                }
            }
            if (isArrived) break;
            dist++;
            
        }

        bw.write((isArrived ? dist : -1) + "");

        bw.flush();
        bw.close();

    }
}

