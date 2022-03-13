import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_5427 {

    static int w, h;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static boolean inRange(int y, int x) {
        return (0 <= y && y < h) && (0 <= x && x < w);
    }
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            w = Integer.parseInt(input[0]);
            h = Integer.parseInt(input[1]);

            // 상근이의 위치
            int x = 0;
            int y = 0;

            // 불의 위치
            int fireX = -1;
            int fireY = -1;

            boolean[][] visited = new boolean[h][w];
            Deque<Integer[]> qSanguen = new ArrayDeque<>();
            Deque<Integer[]> qfire = new ArrayDeque<>();

            char[][] map = new char[h][w];
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '@') {
                        y = i; x = j;
                        visited[y][x] = true;
                        map[i][j] = '.';
                        qSanguen.add(new Integer[] {y, x});

                    } else if(map[i][j] == '*') {   // 불이 여러개 있을 경우...
                        fireY = i; fireX = j;
                        visited[fireY][fireX] = true;
                        qfire.add(new Integer[] {fireY, fireX});
                    }
                }

            }

            // 불이 퍼짐과 상근이의 이동을 동시에
            // 큐를 두개를 세팅하여 BFS를 동시에 돌리는데 이때 불을 먼저 BFS

            int time = 0;
            boolean isArrived = false;

            while (!qSanguen.isEmpty()) {

                int qSize = qfire.size();
                for (int k = 0; k < qSize; k++) {
                    Integer[] qfireFront = qfire.pop();
                    fireY = qfireFront[0];
                    fireX = qfireFront[1];

                    for (int i = 0; i < 4; i++) {
                        int nfireY = fireY + dy[i];
                        int nfireX = fireX + dx[i];

                        if (inRange(nfireY, nfireX) && map[nfireY][nfireX] == '.') {
                            visited[nfireY][nfireX] = true;
                            map[nfireY][nfireX] = '*';
                            qfire.add(new Integer[] {nfireY, nfireX});
                        }
                    }
                }

                qSize = qSanguen.size();
                for (int k = 0; k < qSize; k++) {
                    Integer[] qSanguenFront = qSanguen.pop();
                    y = qSanguenFront[0];
                    x = qSanguenFront[1];

                    if (y == 0 || y == h-1 || x == 0 || x == w-1) {
                        time++;
                        isArrived = true;
                        break;
                    }


                    for (int i = 0; i < 4; i++) {
                        int ny = y + dy[i];
                        int nx = x + dx[i];


                        if (inRange(ny, nx) && !visited[ny][nx] && map[ny][nx] == '.') {
                            visited[ny][nx] = true;
                            qSanguen.add(new Integer[] {ny, nx});
                        }
                    }
                }

                if (isArrived) break;
                time++;
            }

            if (isArrived) bw.write(time + "\n");
            else bw.write("IMPOSSIBLE\n");

        }



        
        bw.flush();
        bw.close();

    }
}

