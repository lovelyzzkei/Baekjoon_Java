import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_10711 {    

    static int h, w;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] castle, nextCastle;
    static boolean[][] visited;

    static boolean inRange(int y, int x) {
        return (0 <= y && y < h) && (0 <= x && x < w);
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        h = Integer.parseInt(input[0]);
        w = Integer.parseInt(input[1]);

        Deque<String> qWater = new ArrayDeque<>();

        castle = new int[h][w];
        nextCastle = new int[h][w];
        visited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            char[] inputArr = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                if (inputArr[j] == '.') {
                    castle[i][j] = nextCastle[i][j] = -1;
                    qWater.add(i + " " + j);
                    visited[i][j] = true;
                }
                else {
                    castle[i][j] = nextCastle[i][j] = inputArr[j] - '0';
                }
            }
        }


        int cnt = -1;
        while (!qWater.isEmpty()) {

            cnt++;

            int qSize = qWater.size();
            for (int t = 0; t < qSize; t++) {
                String[] curr = qWater.pop().split(" ");
                int y = Integer.parseInt(curr[0]);
                int x = Integer.parseInt(curr[1]);

                for (int i = 0; i < 8; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (inRange(ny, nx) && !visited[ny][nx] && castle[ny][nx] != -1) {
                        castle[ny][nx] += castle[y][x];
                        if (castle[ny][nx] <= 0) {
                            castle[ny][nx] = -1;
                            visited[ny][nx] = true;
                            qWater.add(ny + " " + nx);
                        }
                    }
                }
            }            
        }

        bw.write(cnt + "");        
        bw.flush();
        bw.close();

    }
}

