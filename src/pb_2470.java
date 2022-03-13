import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2470 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] solutions = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(solutions);

        int sol = 1_000_000_001;
        int anotherSol = 1_000_000_001;
        for (int i = 0; i < n-1; i++) {
            int cand = -solutions[i];
            boolean isZero = false;

            int lo = i+1;
            int hi = n-1;

            while (lo + 1 < hi) {
                int mid = (lo + hi) / 2;

                if (solutions[mid] == cand) {
                    cand = solutions[mid];
                    isZero = true;
                    break;
                } else if (solutions[mid] > cand) hi = mid;
                else lo = mid;
            }

            if (!isZero) {
                if ((int) Math.abs(solutions[i] + solutions[lo]) < (int) Math.abs(solutions[i] + solutions[hi])) {
                    cand = solutions[lo];
                } else {
                    cand = solutions[hi];
                }
            }
            

            if (Math.abs(solutions[i] + cand) < Math.abs(sol + anotherSol)) {
                sol = solutions[i];
                anotherSol = cand;
            }

        }

        bw.write(sol+ " " + anotherSol);
        bw.flush();
        bw.close();

    }
}
