import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2482 {

    static int n, k;
    static int[][] cache1, cache2;
    static final int INF = 1_000_000_003;


    // 첫번째 색을 칠할 경우 가능한 경우의 수
    static int coloring(int now, int numOfColor) {
        if (numOfColor == k) return 1;
        if (now >= n-1) return 0;
        if (cache1[now][numOfColor] != -1) return cache1[now][numOfColor];
        // for (int[] arr : cache) {
        //     System.out.println(Arrays.toString(arr));
        // }
        // System.out.println();
        return cache1[now][numOfColor] = (coloring(now+1, numOfColor) % INF + coloring(now+2, numOfColor+1) % INF) % INF;

    }

    // 첫번째 색을 칠하지 않을 경우 가능한 경우의 수 
    static int coloring2(int now, int numOfColor) {
        if (numOfColor == k) return 1;
        if (now >= n) return 0;
        if (cache2[now][numOfColor] != -1) return cache2[now][numOfColor];
        // for (int[] arr : cache) {
        //     System.out.println(Arrays.toString(arr));
        // }
        // System.out.println();
        return cache2[now][numOfColor] = (coloring2(now+1, numOfColor) % INF + coloring2(now+2, numOfColor+1) % INF) % INF;

    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        cache1 = new int[n][k];
        cache2 = new int[n][k];
        
        for (int[] arr : cache1) {
            Arrays.fill(arr, -1);
        }

        for (int[] arr : cache2) {
            Arrays.fill(arr, -1);
        }

        int ret = (coloring(2, 1) % INF + coloring2(1, 0) % INF) % INF;

        bw.write(ret + "");
        bw.flush();
        bw.close();

    }
}
