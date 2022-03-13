import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_3055 {

    static int r, c;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static boolean inRange(int y, int x) {
        return (0 <= y && y < r) && (0 <= x && x < c);
    }

    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);

        // 고슴도치의 시작점
        int y = 0;
        int x = 0;

        // 물과 고슴도치의 BFS 정보들을 저장할 큐 선언
        Deque<Integer[]> qWater = new ArrayDeque<>();
        Deque<Integer[]> qHedgehog = new ArrayDeque<>();

        // 맵에서의 방문정보를 저장할 배열 선언
        boolean[][] visited = new boolean[r][c];


        // 지도의 정보를 받아서 배열에 저장
        // 이때 고슴도치와 물의 정보는 따로 처리해서 큐에 넣을 수 있도록 처리

        char[][] map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'S') {
                    y = i; x = j;
                    map[i][j] = '.';
                    visited[y][x] = true;
                    qHedgehog.add(new Integer[] {y, x});
                }

                else if (map[i][j] == '*') {
                    visited[i][j] = true;
                    qWater.add(new Integer[] {i, j});
                }
            }
        }

        int time = 0;
        boolean isArrived = false;

        // 고슴도치를 담은 큐가 빌때까지 BFS를 수행
        while (!qHedgehog.isEmpty()) {

            // 먼저 물의 이동
            int qSize = qWater.size();
            for (int k = 0; k < qSize; k++) {

                Integer[] curr = qWater.pop();
                y = curr[0]; x = curr[1];

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (inRange(ny, nx) && map[ny][nx] == '.') {
                        visited[ny][nx] = true;
                        map[ny][nx] = '*';
                        qWater.add(new Integer[] {ny, nx});
                    }
                }

            }

            // 물이 이동한 다음에 고슴도치 이동
            qSize = qHedgehog.size();
            for (int k = 0; k < qSize; k++) {

                Integer[] curr = qHedgehog.pop();
                y = curr[0]; x = curr[1];

                if (map[y][x] == 'D') {
                    isArrived = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (inRange(ny, nx) && !visited[ny][nx] && (map[ny][nx] == '.' || map[ny][nx] == 'D'))  {
                        visited[ny][nx] = true;
                        qHedgehog.add(new Integer[] {ny, nx});
                    }
                }
            }

            if (isArrived) break;
            time++;

        }

        if (isArrived) bw.write(time + "");
        else bw.write("KAKTUS");
        
        
        bw.flush();
        bw.close();

    }
}

