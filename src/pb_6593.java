import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_6593 {

    static int l, r, c;
    static int exitX, exitY, exitZ;

    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};


    static int[][][] building;
    static boolean[][][] visited;

    static boolean inRange(int z, int y, int x) {
        return (0 <= z && z < l) && (0 <= y && y < r) && (0 <= x && x < c);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String[] input = br.readLine().split(" ");
            l = Integer.parseInt(input[0]);
            r = Integer.parseInt(input[1]);
            c = Integer.parseInt(input[2]);

            if (l == 0 && r == 0 && c == 0) break;

            // 현재 내가 있는 좌표
            int x = 0;
            int y = 0;
            int z = 0;


            // 빌딩 정보 입력 받기
            building = new int[l][r][c];
            for (int i = 0; i < l; i++) {

                for (int j = 0; j < r; j++) {
                    char[] row = br.readLine().toCharArray();

                    for (int k = 0; k < c; k++) {
                        if (row[k] == 'S') {
                            x = k; y = j; z = i;
                        } else if (row[k] == 'E') {
                            exitX = k; exitY = j; exitZ = i;
                        } else if (row[k] == '#') {
                            building[i][j][k] = -1;
                        }
                    }

                }

                br.readLine();  // 빈줄 입력 받기
            }


            // 출발 지점으로부터 BFS를 수행하여 탈출구까지의 최단 시간을 구함
            Deque<Integer[]> q = new ArrayDeque<>();
            q.add(new Integer[] {z, y, x});

            boolean[][][] visited = new boolean[l][r][c];
            visited[z][y][x] = true;

            int time = 0;
            boolean isEscaped = false;
            while (!q.isEmpty()) {
                int qSize = q.size();

                for (int k = 0; k < qSize; k++) {
                    Integer[] front = q.pop();
                    int cz = front[0];
                    int cy = front[1];
                    int cx = front[2];

                    if (cz == exitZ && cy == exitY && cx == exitX) {
                        isEscaped = true;
                        break;
                    }

                    for (int i = 0; i < 6; i++) {
                        int nz = cz + dz[i];
                        int ny = cy + dy[i];
                        int nx = cx + dx[i];
                        
                        if (inRange(nz, ny, nx) && !visited[nz][ny][nx] && building[nz][ny][nx] != -1) {
                            visited[nz][ny][nx] = true;
                            q.add(new Integer[] {nz, ny, nx});
                        }
                    }
                }

                if (isEscaped) {
                    break;
                }
                time++;

            }

            if (isEscaped) {
                bw.write(String.format("Escaped in %d minute(s).\n", time));
            } else {
                bw.write("Trapped!\n");
            }
        }




        
        bw.flush();
        bw.close();

    }
}

