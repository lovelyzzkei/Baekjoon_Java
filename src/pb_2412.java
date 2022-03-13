import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2412 {    

    static int n, T;
    static int[] yIdx;
    static int[][] rockWall;

    static boolean inRange(int y, int x) {
        return (x >= 0) && (y >= 0 && y <= T);
    }

    // Find if there is a hole in (y, x)
    static boolean isHole(int y, int x) {

        // if y is out of bound, return false
        if (!inRange(y, x)) return false;

        // find hole as a binary search using yIdx data
        int start = yIdx[y];    
        int end = (y==T ? n : yIdx[y+1]);
        if (y+1 <= T && yIdx[y+1] == n+1) {
            for (int i = y+2; i <= T; i++) {
                if (yIdx[i] != n+1) {
                    end = yIdx[i];
                    break;
                }
            }
        }

        while (start < end && end <= n) {
            int mid = (start + end) / 2;
            int midX = rockWall[mid][1];

            if (midX == x) return true;
            else if (midX < x) {
                start = mid+1;
            } else {
                end = mid;
            }
        }
        return false;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // Get n, T
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        T = Integer.parseInt(input[1]);

        // Initialize array
        rockWall = new int[n][2];
        yIdx = new int[T+1];

        // Get (x, y) and store at arraylist.get(y)
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            rockWall[i] = new int[] {y, x};
        }

        // Sort array
        Arrays.sort(rockWall, (o1, o2)->{
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        Arrays.fill(yIdx, n+1);
        for (int i = 0; i < n; i++) {
            int currY = rockWall[i][0];
            yIdx[currY] = Math.min(yIdx[currY], i);
        }

        Integer[] start = new Integer[] {0, 0};

        HashMap<String, Boolean> visited = new HashMap<>();
        Deque<Integer[]> q = new ArrayDeque<>();
        q.add(start);
        visited.put("0 0", true);

        int ret = 0;
        boolean isArrived = false;

        while (!q.isEmpty()) {

            int qSize = q.size();
            for (int k = 0; k < qSize; k++) {
                Integer[] curr = q.pop();
                int y = curr[0]; int x = curr[1];

                if (y == T) {
                    isArrived = true;
                    break;
                }

                for (int dy = -2; dy <= 2; dy++) {
                    for (int dx = -2; dx <= 2; dx++) {
                        int ny = y + dy;
                        int nx = x + dx;

                        Integer[] next = new Integer[] {ny, nx};
                        String nextStr = (ny + " " + nx);

                        if (isHole(ny, nx) && visited.get(nextStr) == null) {
                            q.add(next);
                            visited.put(nextStr, true);
                        }
                    }
                }
            }

            if (isArrived) break;
            ret++;
        }

        bw.write((isArrived ? ret : -1) + "");
        bw.flush();
        bw.close();

    }
}

