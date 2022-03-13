import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1449 {

    static int n, l;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        l = Integer.parseInt(input[1]);
        
        int[] pipe = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(pipe);

        double start = pipe[0] - 0.5;
        double end = start + l;
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (start <= pipe[i] - 0.5 && pipe[i] + 0.5 <= end) continue;
            start = pipe[i] - 0.5;
            end = start + l;
            cnt++;
        }

        bw.write(String.format("%d", cnt));
        bw.flush();
        bw.close();

    }
}
