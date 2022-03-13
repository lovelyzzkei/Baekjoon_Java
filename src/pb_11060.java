import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class pb_11060 {

    static int n;
    static int[] a, cache;
    static final int INF = 1_000_000;

    // start에서 가장 오른쪽 끝칸까지 갈 수 있는 최소 점프의 횟수
    static int jump(int start) {
        if (start == n - 1) return 0;
        if (start > n - 1) return INF;
        if (cache[start] != -1) return cache[start];
        cache[start] = INF;
        for (int i = 1; i <= a[start]; i++) {
            cache[start] = Math.min(cache[start], jump(start + i) + 1); 
        }
        return cache[start];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        a = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cache = new int[n];
        Arrays.fill(cache, -1);

        int ret = jump(0);
        ret = (ret >= INF) ? -1 : ret;
        bw.write(String.format("%d", ret));
        bw.flush();
        bw.close();

    }
}
