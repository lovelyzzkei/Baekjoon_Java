import java.io.*;
import java.util.*;

public class pb_15989 {

    static int n;
    static int[][] cache;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int t = Integer.parseInt(br.readLine());
        cache = new int[10001][4];
        
        cache[1][1] = 1; cache[1][2] = 0; cache[1][3] = 0;
        cache[2][1] = 1; cache[2][2] = 1; cache[2][3] = 0;
        cache[3][1] = 1; cache[3][2] = 1; cache[3][3] = 1;
        

        for (int i = 4; i <= 10000; i++) {
            cache[i][1] = 1;
            cache[i][2] = cache[i-2][1] + cache[i-2][2];
            cache[i][3] = cache[i-3][1] + cache[i-3][2] + cache[i-3][3];
        }
        for (int j = 0; j < t; j++) {
            n = Integer.parseInt(br.readLine());
            
            bw.write(String.format("%d\n", cache[n][1] + cache[n][2] + cache[n][3]));
        }
        bw.flush();
        bw.close();

    }
}
