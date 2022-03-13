import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_2473 {    

    static int n;
    static long[] sols;
    static final long INF = 1_000_000_001L;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        sols = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(sols);

        long ret = 3*INF;
        String ans = sols[0] + " " + sols[1] + " " + sols[2];

        for (int i = 0; i < n-2; i++) {
            for (int j = i+1; j < n-1; j++) {

                long twoMixedSol = sols[i] + sols[j];

                // Search the nearest solution that makes twoMixedSol as 0
                // Range : j+1 ~ n-1
                long target = -twoMixedSol;
                int start = j+1; int end = n;

                // Binary search
                while (start + 1 < end) {
                    int mid = (start + end) / 2;
                    if (sols[mid] >= target) {
                        end = mid;
                    } else {
                        start = mid;
                    }
                }

                end = (end >= n) ? n-1 : end;
                // On [start, start+1], find the nearest solution
                
                long cand1 = Math.abs(twoMixedSol + sols[start]);
                long cand2 = Math.abs(twoMixedSol + sols[end]);
                long lastSol = (cand1 < cand2) ? sols[start] : sols[end];
                
                if (Math.abs(twoMixedSol + lastSol) < Math.abs(ret)) {
                    ret = Math.abs(twoMixedSol + lastSol);
                    ans = sols[i] + " " + sols[j] + " " + lastSol;
                }

            }
        }


        bw.write(ans);


        bw.flush();
        bw.close();

    }
}

