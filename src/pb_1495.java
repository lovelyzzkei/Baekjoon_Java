import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_1495 {

    static int n, s, m;
    static int[] cache, volume;

    static int changeVol(int start, int before) {
        if (start == n) return 0;
        if (cache[start] != -1) return cache[start];
        return 0;

    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        s = Integer.parseInt(input[1]);
        m = Integer.parseInt(input[2]);

        volume = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cache = new int[n];
        Arrays.fill(cache, -1);

        int ret = changeVol(0, 0);

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
