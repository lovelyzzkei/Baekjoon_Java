import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class pb_2110 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);

        int[] house = new int[n];
        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        int lo = 0;
        int hi = 1_000_000_000;

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            int start = house[0];
            int cnt = 1;
            for (int i = 1; i < n; i++) {
                int end = house[i];
                if (end - start >= mid) {
                    cnt++;
                    start = end;
                }
            }

            if (cnt < c) hi = mid;
            else lo = mid;
        }
        

        bw.write(lo + "");
        bw.flush();
        bw.close();

    }
}
