import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1562 {

    static int n;
    static int[][] dnum = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static long[][][] cache = new long[101][10][10];
    static final int INF = 1_000_000_000;

    static boolean inRange(int front, int back) {
        return (0 <= front && front < 10) && (0 <= back && back < 10);
    }

    static long calcStairNumber(int length, int frontNum, int backNum) {
        if (length == n) return 1;
        if (cache[length][frontNum][backNum] != -1) return cache[length][frontNum][backNum];

        cache[length][frontNum][backNum] = 0;
        for (int[] d : dnum) {
            int i = d[0], j = d[1];    
            if (inRange(frontNum + i, backNum + j) && frontNum + i != 0) {
                cache[length][frontNum][backNum] += (calcStairNumber(length + 1, frontNum + i, backNum + j) % INF);
            }
            
        }

        // for (int[][] arr : cache) {
        //     for (int[] tmp : arr) {
        //         System.out.print(Arrays.toString(tmp) + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        return cache[length][frontNum][backNum] % INF;
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());

        for (long[][] arr : cache) {
            for (long[] tmp : arr) {
                long[] initialize = new long[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
                System.arraycopy(initialize, 0, tmp, 0, 10);
            }
        }

        // cache[10][9][0] = 1;

        // long ret = calcStairNumber(11, 8, 0) + calcStairNumber(11, 9, 1);

        cache[10][9][0] = -1;
        long ans = calcStairNumber(10, 9, 0);
        for (int i = 11; i <= 40; i++) {
            n = i;
            ans += calcStairNumber(10, 9, 0);
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();

    }
}
