import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_16434 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int atk = Integer.parseInt(input[1]);

        int[] t = new int[n];
        int[] a = new int[n];
        int[] h = new int[n];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            t[i] = Integer.parseInt(input[0]);
            a[i] = Integer.parseInt(input[1]);
            h[i] = Integer.parseInt(input[2]);
        }

        long lo = 0;
        long hi = Long.MAX_VALUE; 

        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;

            long hCUR = mid;
            long hATK = atk;
            boolean isFailed = false;

            for (int i = 0; i < n; i++) {
                if (t[i] == 1) { 
                    long cntOfAtk = (h[i] % hATK == 0) ? ((h[i] / hATK) - 1) : (h[i] / hATK);
                    if (hCUR - (a[i] * cntOfAtk) > 0) {
                        hCUR -= (a[i] * cntOfAtk);
                    } else {
                        isFailed = true;
                        break;
                    }
                }
                if (t[i] == 2) {
                    hATK += a[i];
                    hCUR = (hCUR + h[i] <= mid) ? hCUR + h[i] : mid;
                }
            }

            if (isFailed) lo = mid;
            else hi = mid;
        }
        

        bw.write(hi + "");
        bw.flush();
        bw.close();

    }
}
