import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_14226 {

    static int s;
    static int[] time, cache;
    static final int INF = 1_000_000;

    static int makeEmo(int num) {
        if (num == 0) return 0;

        if (cache[num] != -1) return cache[num];
        
    
        return cache[num];
    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        s = Integer.parseInt(br.readLine());
        cache = new int[s + 1];
        Arrays.fill(cache, -1);
        cache[1] = 0;

        int ret = makeEmo(s);

        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
