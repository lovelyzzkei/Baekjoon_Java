import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2718 {    

    static int n;
    static int[] cacheTwo, cacheFour;

    static int tilingTwo(int n) {
        if (n == 0 || n == 1) return cacheTwo[n] = 1;
        if (cacheTwo[n] != -1) return cacheTwo[n];
        return cacheTwo[n] = tilingTwo(n-1) + tilingTwo(n-2);
    }
    
    static int tilingFour(int n) {
        if (n == 0 || n == 1) return cacheFour[n] = 1;
        if (cacheFour[n] != -1) return cacheFour[n];
        int cand = tilingTwo(n) * tilingTwo(n);
        for (int len = n-2; len >= 0; len--) {
            cand += tilingFour(n-len-2) * tilingTwo(len) * tilingTwo(len);
        }
        return cacheFour[n] = cand;
        
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());

            cacheTwo = new int[n+1];
            cacheFour = new int[n+1];

            Arrays.fill(cacheTwo, -1);
            Arrays.fill(cacheFour, -1);

            // tilingTwo(n);
            int ret = tilingFour(n);

            System.out.println(Arrays.toString(cacheTwo));
            System.out.println(Arrays.toString(cacheFour));

            bw.write(ret + "\n");
        }
        
        bw.flush();
        bw.close();

    }
}

