import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_11084 { 
    
    static int r, c, shortestDist;
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static long[][][] cache;
    static boolean[][] visited;
    static final int INF = 1_000_000_009;

    static boolean inRange(int y, int x) {
        return (0 < y && y <= r) && (0 < x && x <= c);
    }


    static int calcShortestDist() {
        // Find minimum distance
        String start = "1 1";
        Deque<String> q = new ArrayDeque<>();
        q.add(start);

        int ret = 0;
        boolean isArrived = false;
        visited = new boolean[r+1][c+1];

        while (!q.isEmpty()) {

            int qSize = q.size();
            for (int t = 0; t < qSize; t++) {
                int[] curr = Stream.of(q.pop().split(" ")).mapToInt(Integer::parseInt).toArray();
                int y = curr[0]; int x = curr[1];

                if (y == r && x == c) {
                    isArrived = true;
                    break;
                }

                for (int i = 0; i < 8; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (inRange(ny, nx) && !visited[ny][nx]) {
                        String next = ny + " " + nx;
                        visited[ny][nx] = true;
                        q.add(next);
                    }
                }
            }
            if (isArrived) break;
            ret++;
        }
        return (isArrived ? ret : -1);
    }


    static long calcShortestPath(int y, int x, int dist) {
        if (y == r && x == c && dist == shortestDist) return cache[y][x][dist] = 1;
        if (dist == shortestDist) return cache[y][x][dist] = 0;
        if (cache[y][x][dist] != -1) return cache[y][x][dist];

        cache[y][x][dist] = 0;
        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (inRange(ny, nx)) {
                cache[y][x][dist] += (calcShortestPath(ny, nx, dist+1) % INF);
            }
        }
        return cache[y][x][dist] % INF;
    }   

    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);


        // Calculate shortest distance
        // return -1 if there is no path
        shortestDist = calcShortestDist();

        if (shortestDist == -1) {
            bw.write("None");
        } else {

            // Calculate the number of shortest path
            cache = new long[r+1][c+1][shortestDist+1];
            for (long[][] twoDimArr : cache) {
                for (long[] arr : twoDimArr) {
                    Arrays.fill(arr, -1);
                }
            }

            long numOfShortestPath = calcShortestPath(1, 1, 0);
            bw.write(shortestDist + " " + numOfShortestPath);
        }
        
        



        bw.flush();
        bw.close();

    }
}

