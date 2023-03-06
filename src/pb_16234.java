import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_16234 {    

    static int n, l, r;
    static int[][] A;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[][] visited;

    static boolean isOpenBoundary(int a, int b) {
        int diff = Math.abs(a - b);
        return (l <= diff && diff <= r);
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < n);
    }


    static void bfs(int y, int x) {
        Deque<Integer[]> dq = new ArrayDeque<>();
        visited[y][x] = true;
        dq.add(new Integer[]{y, x});

        int sum = 0;
        int numOfNode = 0;

        while (!dq.isEmpty()) {
            int qSize = dq.size();
            
            for (int t = 0; t < qSize; t++) {
                Integer[] curr = dq.pop();
                y = curr[0];
                x = curr[1];

                sum += A[y][x];
                numOfNode++;

                // for (int next : graph.get(y*n+x)) {
                //     if (!visited[next]) {
                //         visited[next] = true;
                //         dq.add(next);
                //     }
                // }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        l = Integer.parseInt(input[1]);
        r = Integer.parseInt(input[2]);

        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(arr, 0, A[i], 0, n);
        }

        graph = new ArrayList<>();
        for (int i = 0; i < n*n; i++) {
            graph.add(new ArrayList<>());
        }

        int cnt = 0;

        while (true) {
            boolean isMoved = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int dir = 0; dir < 4; dir++) {
                        int ny = i + dy[dir];
                        int nx = j + dx[dir];
                        if (inRange(ny, nx) && isOpenBoundary(A[i][j], A[ny][nx])) {
                            isMoved = true;
                            graph.get(i*n+j).add(ny*n+nx);
                        }
                    }
                }
            }

            if (!isMoved) break;

            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }

            for (ArrayList al : graph) {
                System.out.println(al.toString());
            }

            break;
        }

        bw.flush();
        bw.close();

    }
}
