import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_5582 {

    static char[] a, b;
    static int[][] cache;
    

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 앞에 더미 데이터 추가
        a = (" " + br.readLine()).toCharArray();
        b = (" " + br.readLine()).toCharArray();

        cache = new int[b.length][a.length];

        for (int i = 1; i <= b.length-1; i++) {
            for (int j = 1; j <= a.length-1; j++) {
                if (a[j] == b[i]) cache[i][j] = cache[i-1][j-1] + 1;
                // else cache[i][j] = Math.max(cache[i-1][j], cache[i][j-1]);
            }
        }

        int ret = 0;
        for (int i = 1; i <= b.length-1; i++) {
            for (int j = 1; j <= a.length-1; j++) {
                ret = Math.max(ret, cache[i][j]);
            }
        }
        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
