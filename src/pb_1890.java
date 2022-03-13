import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1890 {

    static int n;
    static int[][] board;
    static long[][] cache;

    static long jump(int y, int x) {
        if (y >= n || x >= n) return 0;
        if (y == n-1 && x == n-1) return 1;
        if (cache[y][x] != -1) return cache[y][x];

        int jumpSize = board[y][x];
        if (jumpSize == 0) {
            return cache[y][x] = 0;
        } else {
            return cache[y][x] = jump(y+jumpSize, x) + jump(y, x+jumpSize);
        }
        
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(input, 0, board[i], 0, n);
        }

        cache = new long[n][n];
        for (long[] arr : cache) {
            Arrays.fill(arr, -1);
        }

        long ret = jump(0, 0);

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
