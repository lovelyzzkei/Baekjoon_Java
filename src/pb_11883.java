import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_11883 {    

    static int n;
    static int[] birthNum = {8, 5, 3};
    static int[] cacheNum, cacheLen;
    static final int INF = 1_000_001;

    static void dp() {
        cacheNum[0] = 0;
        cacheLen[0] = 0;

        for (int i = 1; i < INF; i++) {
            for (int b : birthNum) {
                if (i-b >= 0 && cacheNum[i-b] != -1 && cacheLen[i-b] + 1< cacheLen[i]) {
                    cacheNum[i] = b;
                    cacheLen[i] = cacheLen[i-b] + 1;
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        cacheNum = new int[INF];
        cacheLen = new int[INF];

        Arrays.fill(cacheNum, -1);
        Arrays.fill(cacheLen, INF);
        
        
        dp();
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());

            int ret = cacheNum[n];
            if (ret != -1){
                StringBuffer sb = new StringBuffer();
                while (n != 0) {
                    sb.append(cacheNum[n]);
                    n -= cacheNum[n];
                }
                sb = sb.reverse();
                System.out.println(sb);
            } else {
                System.out.println(-1);
            }

        }


        bw.flush();
        bw.close();

    }
}

