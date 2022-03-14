import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_11883 {    

    static int n;
    static int[] birthNum = {8, 5, 3};
    static int[] cache;
    static final int INF = 1_000_001;

    static void dp() {
        cache[0] = 0;
        for (int i = 1; i < INF; i++) {
            for (int b : birthNum) {
                if (i-b >= 0 && cache[i-b] != -1) {
                    cache[i] = Math.max(cache[i], b);
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        cache = new int[INF];
        Arrays.fill(cache, -1);

        
        dp();
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());

            int ret = cache[n];
            if (ret != -1){
                StringBuffer sb = new StringBuffer();
                while (n != 0) {
                    sb.append(cache[n]);
                    n -= cache[n];
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

